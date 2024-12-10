package com.example.education_centre.service.group_permission;

import com.example.education_centre.dto.group_permission.CheckGroupPermission;
import com.example.education_centre.dto.permission.CheckPermission;
import com.example.education_centre.model.GroupPermission;
import com.example.education_centre.model.Permission;
import com.example.education_centre.repository.GroupPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupPermissionServiceImpl implements GroupPermissionService{
    @Autowired
    private GroupPermissionRepository groupPermissionRepository;

    @Override
    public List<GroupPermission> findAll() {
        return groupPermissionRepository.findAll();
    }

    @Override
    public CheckGroupPermission findByIdCheckGroupPermission(Integer id) {
        GroupPermission groupPermission = groupPermissionRepository.findById(id).orElse(null);

        CheckGroupPermission checkGroupPermission = new CheckGroupPermission();
        checkGroupPermission.setId(groupPermission.getId());
        checkGroupPermission.setDisplayName(groupPermission.getDisplayName());
        checkGroupPermission.setDescription(groupPermission.getDescription());

        return checkGroupPermission;
    }

    @Override
    public GroupPermission findById(Integer id) {
        return groupPermissionRepository.findById(id).orElse(null);
    }

    @Override
    public List<GroupPermission> getGroupPermissionsByPageAndSize(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return groupPermissionRepository.findAll(pageable).getContent();
    }

    @Override
    public List<GroupPermission> searchGroupPermissions(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return groupPermissionRepository.findByDisplayNameContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public long getTotalGroupPermissionCount() {
        return groupPermissionRepository.count();
    }

    @Override
    public long getSearchResultCount(String keyword) {
        return groupPermissionRepository.countByDisplayNameContainingIgnoreCase(keyword);
    }

    @Override
    public void save(CheckGroupPermission data) {
        Optional<GroupPermission> existingGroupPermission = groupPermissionRepository.findById(data.getId());
        GroupPermission groupPermission = existingGroupPermission.orElseGet(GroupPermission::new);
        System.out.println(groupPermission);

        groupPermission.setDescription(data.getDescription());

        groupPermissionRepository.save(groupPermission);
    }
}
