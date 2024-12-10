package com.example.education_centre.repository;

import com.example.education_centre.model.GroupPermission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupPermissionRepository extends JpaRepository<GroupPermission, Integer> {
    List<GroupPermission> findByDisplayNameContainingIgnoreCase(String displayName, Pageable pageable);
    long count();
    long countByDisplayNameContainingIgnoreCase(String displayName);

    @Query("SELECT gp FROM GroupPermission gp JOIN FETCH gp.permissions")
    List<GroupPermission> findAllWithPermissions();
}
