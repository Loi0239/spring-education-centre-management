package com.example.education_centre.service.role;

import com.example.education_centre.dto.role.CheckRole;
import com.example.education_centre.model.GroupPermission;
import com.example.education_centre.model.PermissionRole;
import com.example.education_centre.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    CheckRole findByIdCheckRole(Integer id);
    Role findById(Integer id);
    List<Role> getRolesByPageAndSize(int page, int size);
    List<Role> searchRoles(String keyword, int page, int size);
    long getTotalRoleCount();
    long getSearchResultCount(String keyword);
    List<GroupPermission> getAllGroupPermissions();
    List<PermissionRole> getPermissionRolesByRoleId(Integer roleId);
    void updateRolePermissions(Integer roleId, List<Integer> permissionIds);
    void save(CheckRole data);
}
