package com.example.education_centre.dto.user;

import com.example.education_centre.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    long id;
    String userCode;
    String name;
    String email;
    boolean status;
    String roleName;

    public UserResponse(User user){
        this.id = user.getId();
        this.userCode = user.getUserCode();
        this.name = user.getName();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.roleName = user.getRole().getDisplayName();
    }
}
