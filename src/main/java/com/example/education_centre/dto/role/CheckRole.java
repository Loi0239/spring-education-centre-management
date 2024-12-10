package com.example.education_centre.dto.role;

import com.example.education_centre.model.GroupPermission;
import com.example.education_centre.model.Permission;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckRole {
    int id;
    String displayName;
    String description;
//    List<GroupPermission> groupPermissions;
//    List<Permission> permissions;
}
