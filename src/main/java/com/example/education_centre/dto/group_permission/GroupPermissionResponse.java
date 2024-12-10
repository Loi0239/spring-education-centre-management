package com.example.education_centre.dto.group_permission;

import com.example.education_centre.model.GroupPermission;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupPermissionResponse {
    int id;
    String displayName;
    String description;

    public GroupPermissionResponse(GroupPermission groupPermission){
        this.id = groupPermission.getId();
        this.displayName = groupPermission.getDisplayName();
        this.description = groupPermission.getDescription();
    }
}
