package com.example.education_centre.service.permission;

import com.example.education_centre.dto.permission.CheckPermission;
import com.example.education_centre.dto.role.CheckRole;
import com.example.education_centre.model.Permission;
import com.example.education_centre.model.Role;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
    CheckPermission findByIdCheckPermission(Integer id);
    Permission findById(Integer id);
    List<Permission> getPermissionsByPageAndSize(int page, int size);
    List<Permission> searchPermissions(String keyword, int page, int size);
    long getTotalPermissionCount();
    long getSearchResultCount(String keyword);
    void save(CheckPermission data);
}
