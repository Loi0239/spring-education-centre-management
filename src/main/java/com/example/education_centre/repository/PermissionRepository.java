package com.example.education_centre.repository;

import com.example.education_centre.model.Permission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findByDisplayNameContainingIgnoreCase(String displayName, Pageable pageable);
    long count();
    long countByDisplayNameContainingIgnoreCase(String displayName);
}
