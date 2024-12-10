package com.example.education_centre.controller.api;

import com.example.education_centre.dto.permission.PermissionResponse;
import com.example.education_centre.dto.role.RoleResponse;
import com.example.education_centre.model.Permission;
import com.example.education_centre.model.Role;
import com.example.education_centre.service.permission.PermissionService;
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
@RequestMapping("/permission")
public class PermissionApi {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/record")
    public ResponseEntity<Map<String, Object>> getPermissionRecords(
            @RequestParam int page,
            @RequestParam int size
    ) {
        // Fetch data logic (giả lập)
        List<Permission> permissions = permissionService.getPermissionsByPageAndSize(page, size);
        List<PermissionResponse> permissionResponses = permissions.stream().map(PermissionResponse::new).toList();
        long totalRPermissions = permissionService.getTotalPermissionCount();

        // Tạo phản hồi JSON
        Map<String, Object> response = new HashMap<>();
        response.put("permissions", permissionResponses);
        response.put("totalPages", (int) Math.ceil((double) totalRPermissions / size));
        response.put("currentPage", page);

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchPermissions(
            @RequestParam("query") String keyword,
            @RequestParam int page,
            @RequestParam int size
    ) {
        // Fetch search results logic (giả lập)
        List<Permission> permissions = permissionService.searchPermissions(keyword, page, size);
        long totalPermissions = permissionService.getSearchResultCount(keyword);
        List<PermissionResponse> permissionResponses = permissions.stream().map(PermissionResponse::new).toList();

        // Tạo phản hồi JSON
        Map<String, Object> response = new HashMap<>();
        response.put("rpermissions", permissionResponses);
        response.put("totalPages", (int) Math.ceil((double) totalPermissions / size));
        response.put("currentPage", page);

        return ResponseEntity.ok(response);
    }
}
