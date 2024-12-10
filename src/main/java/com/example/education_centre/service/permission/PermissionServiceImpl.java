package com.example.education_centre.service.permission;

import com.example.education_centre.dto.permission.CheckPermission;
import com.example.education_centre.dto.role.CheckRole;
import com.example.education_centre.model.Permission;
import com.example.education_centre.model.Role;
import com.example.education_centre.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public CheckPermission findByIdCheckPermission(Integer id) {
        Permission permission = permissionRepository.findById(id).orElse(null);

        CheckPermission checkPermission = new CheckPermission();
        checkPermission.setId(permission.getId());
        checkPermission.setDisplayName(permission.getDisplayName());
        checkPermission.setDescription(permission.getDescription());
        checkPermission.setIdGroup(permission.getGroupPermission().getId());

        return checkPermission;
    }

    @Override
    public Permission findById(Integer id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Permission> getPermissionsByPageAndSize(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return permissionRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Permission> searchPermissions(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return permissionRepository.findByDisplayNameContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public long getTotalPermissionCount() {
        return permissionRepository.count();
    }

    @Override
    public long getSearchResultCount(String keyword) {
        return permissionRepository.countByDisplayNameContainingIgnoreCase(keyword);
    }

    @Override
    public void save(CheckPermission data) {
        Optional<Permission> existingPermission = permissionRepository.findById(data.getId());
        Permission permission = existingPermission.orElseGet(Permission::new);
        System.out.println(permission);

        permission.setDescription(data.getDescription());

        permissionRepository.save(permission);
    }
}
