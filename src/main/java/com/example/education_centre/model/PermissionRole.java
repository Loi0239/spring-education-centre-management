package com.example.education_centre.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "permission_role", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"role_id", "permission_id"})
})
public class PermissionRole {
    @EmbeddedId // Chỉ định đây là khóa chính kết hợp
    private PermissionRoleId id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission;

}
