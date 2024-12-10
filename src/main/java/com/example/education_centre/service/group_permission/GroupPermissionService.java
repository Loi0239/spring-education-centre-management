package com.example.education_centre.service.group_permission;

import com.example.education_centre.dto.group_permission.CheckGroupPermission;
import com.example.education_centre.dto.permission.CheckPermission;
import com.example.education_centre.model.GroupPermission;
import com.example.education_centre.model.Permission;

import java.util.List;

public interface GroupPermissionService {
    List<GroupPermission> findAll();
    CheckGroupPermission findByIdCheckGroupPermission(Integer id);
    GroupPermission findById(Integer id);
    List<GroupPermission> getGroupPermissionsByPageAndSize(int page, int size);
    List<GroupPermission> searchGroupPermissions(String keyword, int page, int size);
    long getTotalGroupPermissionCount();
    long getSearchResultCount(String keyword);
    void save(CheckGroupPermission data);
}
