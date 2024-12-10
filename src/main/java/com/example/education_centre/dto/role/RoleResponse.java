package com.example.education_centre.dto.role;

import com.example.education_centre.model.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    int id;
    String displayName;
    String description;
    List<String> namePermissions;

    public RoleResponse(Role role){
        this.id = role.getId();
        this.displayName = role.getDisplayName();
        this.description = role.getDescription();
        this.namePermissions = role.getPermissionRoles().stream()
                .map(permissionRole -> permissionRole.getPermission().getDisplayName())
                .toList();

    }
}
