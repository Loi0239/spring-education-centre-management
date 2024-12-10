package com.example.education_centre.controller.api;

import com.example.education_centre.dto.role.RoleResponse;
import com.example.education_centre.model.Role;
import com.example.education_centre.service.role.RoleService;
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
@RequestMapping("/role")
public class RoleApi {
    @Autowired
    private RoleService roleService;

    @GetMapping("/record")
    public ResponseEntity<Map<String, Object>> getRoleRecords(
            @RequestParam int page,
            @RequestParam int size
    ) {
        // Fetch data logic (giả lập)
        List<Role> roles = roleService.getRolesByPageAndSize(page, size);
        List<RoleResponse> roleResponses = roles.stream().map(RoleResponse::new).toList();
        long totalRoles = roleService.getTotalRoleCount();

        // Tạo phản hồi JSON
        Map<String, Object> response = new HashMap<>();
        response.put("roles", roleResponses);
        response.put("totalPages", (int) Math.ceil((double) totalRoles / size));
        response.put("currentPage", page);

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchRoles(
            @RequestParam("query") String keyword,
            @RequestParam int page,
            @RequestParam int size
    ) {
        // Fetch search results logic (giả lập)
        List<Role> roles = roleService.searchRoles(keyword, page, size);
        long totalRoles = roleService.getSearchResultCount(keyword);
        List<RoleResponse> roleResponses = roles.stream().map(RoleResponse::new).toList();

        // Tạo phản hồi JSON
        Map<String, Object> response = new HashMap<>();
        response.put("roles", roleResponses);
        response.put("totalPages", (int) Math.ceil((double) totalRoles / size));
        response.put("currentPage", page);

        return ResponseEntity.ok(response);
    }
}
