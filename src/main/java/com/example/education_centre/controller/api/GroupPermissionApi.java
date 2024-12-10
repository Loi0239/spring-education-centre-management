package com.example.education_centre.controller.api;

import com.example.education_centre.dto.group_permission.GroupPermissionResponse;
import com.example.education_centre.model.GroupPermission;
import com.example.education_centre.service.group_permission.GroupPermissionService;
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
@RequestMapping("/group-permission")
public class GroupPermissionApi {
    @Autowired
    private GroupPermissionService groupPermissionService;

    @GetMapping("/record")
    public ResponseEntity<Map<String, Object>> getGroupPermissionRecords(
            @RequestParam int page,
            @RequestParam int size
    ) {
        // Fetch data logic (giả lập)
        List<GroupPermission> groupPermissions = groupPermissionService.getGroupPermissionsByPageAndSize(page, size);
        List<GroupPermissionResponse> groupPermissionResponses = groupPermissions.stream().map(GroupPermissionResponse::new).toList();
        long totalGroupPermissions = groupPermissionService.getTotalGroupPermissionCount();

        // Tạo phản hồi JSON
        Map<String, Object> response = new HashMap<>();
        response.put("groupPermissions", groupPermissionResponses);
        response.put("totalPages", (int) Math.ceil((double) totalGroupPermissions / size));
        response.put("currentPage", page);

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchGroupPermissions(
            @RequestParam("query") String keyword,
            @RequestParam int page,
            @RequestParam int size
    ) {
        // Fetch search results logic (giả lập)
        List<GroupPermission> groupPermissions = groupPermissionService.searchGroupPermissions(keyword, page, size);
        long totalGroupPermissions = groupPermissionService.getSearchResultCount(keyword);
        List<GroupPermissionResponse> groupPermissionResponses = groupPermissions.stream().map(GroupPermissionResponse::new).toList();

        // Tạo phản hồi JSON
        Map<String, Object> response = new HashMap<>();
        response.put("groupPermissions", groupPermissionResponses);
        response.put("totalPages", (int) Math.ceil((double) totalGroupPermissions / size));
        response.put("currentPage", page);

        return ResponseEntity.ok(response);
    }
}
