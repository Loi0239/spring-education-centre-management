package com.example.education_centre.repository;

import com.example.education_centre.model.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByDisplayNameContainingIgnoreCase(String displayName, Pageable pageable);
    long count();
    long countByDisplayNameContainingIgnoreCase(String displayName);

    @Query("SELECT r FROM Role r JOIN FETCH r.permissionRoles pr JOIN FETCH pr.permission WHERE r.id = :roleId")
    Role findRoleWithPermissions(@Param("roleId") Integer roleId);
}
