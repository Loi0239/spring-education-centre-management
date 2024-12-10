package com.example.education_centre.dto.permission;

import com.example.education_centre.model.Permission;
import com.example.education_centre.model.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionResponse {
    int id;
    String displayName;
    String description;
    String nameGroupPermission;

    public PermissionResponse(Permission permission){
        this.id = permission.getId();
        this.displayName = permission.getDisplayName();
        this.description = permission.getDescription();
        this.nameGroupPermission = permission.getGroupPermission().getDisplayName();
    }
}
