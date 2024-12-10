package com.example.education_centre.repository;

import com.example.education_centre.model.PermissionRole;
import com.example.education_centre.model.PermissionRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRoleRepository extends JpaRepository<PermissionRole, PermissionRoleId> {
    @Query("SELECT pr FROM PermissionRole pr WHERE pr.role.id = :roleId")
    List<PermissionRole> findByRoleId(@Param("roleId") Integer roleId);
}
