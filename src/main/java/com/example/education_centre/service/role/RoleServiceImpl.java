package com.example.education_centre.service.role;

import com.example.education_centre.dto.role.CheckRole;
import com.example.education_centre.model.*;
import com.example.education_centre.repository.GroupPermissionRepository;
import com.example.education_centre.repository.PermissionRepository;
import com.example.education_centre.repository.PermissionRoleRepository;
import com.example.education_centre.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private GroupPermissionRepository groupPermissionRepository;
    @Autowired
    private PermissionRoleRepository permissionRoleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public CheckRole findByIdCheckRole(Integer id) {
        Role role = roleRepository.findById(id).orElse(null);

        CheckRole checkRole = new CheckRole();
        checkRole.setId(role.getId());
        checkRole.setDisplayName(role.getDisplayName());
        checkRole.setDescription(role.getDescription());

        return checkRole;
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> getRolesByPageAndSize(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return roleRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Role> searchRoles(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return roleRepository.findByDisplayNameContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public long getTotalRoleCount() {
        return roleRepository.count();
    }

    @Override
    public long getSearchResultCount(String keyword) {
        return roleRepository.countByDisplayNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<GroupPermission> getAllGroupPermissions() {
        return groupPermissionRepository.findAllWithPermissions();
    }

    @Override
    public List<PermissionRole> getPermissionRolesByRoleId(Integer roleId) {
        return permissionRoleRepository.findByRoleId(roleId);
    }

    @Override
    public void updateRolePermissions(Integer roleId, List<Integer> permissionIds) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        List<Permission> permissions = permissionRepository.findAllById(permissionIds);

        // Xóa quyền cũ
        List<PermissionRole> currentPermissions = permissionRoleRepository.findByRoleId(roleId);
        permissionRoleRepository.deleteAll(currentPermissions);

        // Thêm quyền mới
        List<PermissionRole> newPermissions = permissions.stream()
                .map(permission -> {
                    PermissionRole permissionRole = new PermissionRole();
                    PermissionRoleId id = new PermissionRoleId();
                    id.setRoleId(roleId);
                    id.setPermissionId(permission.getId());

                    permissionRole.setId(id);
                    permissionRole.setRole(role);  // Gán role vào PermissionRole
                    permissionRole.setPermission(permission);
                    return permissionRole;
                })
                .collect(Collectors.toList());
        permissionRoleRepository.saveAll(newPermissions);
    }

    @Override
    public void save(CheckRole data) {
        Optional<Role> existingRole = roleRepository.findById(data.getId());
        Role role = existingRole.orElseGet(Role::new);

        role.setDisplayName(data.getDisplayName());
        role.setDescription(data.getDescription());

        roleRepository.save(role);
    }
}
