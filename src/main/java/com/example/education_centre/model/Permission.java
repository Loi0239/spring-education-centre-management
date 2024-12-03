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
//    @CreatedDate
//    @Column(name = "created_at", updatable = false, nullable = false)
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_permission", referencedColumnName = "id")
    GroupPermission groupPermission;

    @OneToMany(mappedBy = "permission")
    private List<PermissionRole> permissionRoles;
}
