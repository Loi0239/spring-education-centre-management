package com.example.education_centre.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Permission extends Auditable {
    @Id
    @GeneratedValue
    int id;
    @Column(unique = true, nullable = false)
    String name;
    @Column(name="display_name", unique = true, nullable = false)
    String displayName;
    String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_permission", referencedColumnName = "id")
    GroupPermission groupPermission;

    @ToString.Exclude
    @OneToMany(mappedBy = "permission")
    List<PermissionRole> permissionRoles;
}
