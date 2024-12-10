package com.example.education_centre.controller.api;

import com.example.education_centre.dto.user.UserResponse;
import com.example.education_centre.model.User;
import com.example.education_centre.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/record")
    public ResponseEntity<Map<String, Object>> getUserRecords(
            @RequestParam int page,
            @RequestParam int size
    ) {
        // Fetch data logic (giả lập)
        List<User> users = userService.getUsersByPageAndSize(page, size);
        List<UserResponse> userResponses = users.stream().map(UserResponse::new).toList();
        long totalUsers = userService.getTotalUserCount();

        // Tạo phản hồi JSON
        Map<String, Object> response = new HashMap<>();
        response.put("users", userResponses);
        response.put("totalPages", (int) Math.ceil((double) totalUsers / size));
        response.put("currentPage", page);

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchUsers(
            @RequestParam("query") String keyword,
            @RequestParam int page,
            @RequestParam int size
    ) {
        // Fetch search results logic (giả lập)
        List<User> users = userService.searchUsers(keyword, page, size);
        long totalUsers = userService.getSearchResultCount(keyword);
        List<UserResponse> userResponses = users.stream().map(UserResponse::new).toList();

        // Tạo phản hồi JSON
        Map<String, Object> response = new HashMap<>();
        response.put("users", userResponses);
        response.put("totalPages", (int) Math.ceil((double) totalUsers / size));
        response.put("currentPage", page);

        return ResponseEntity.ok(response);
    }
}
