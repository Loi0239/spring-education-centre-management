package com.example.education_centre.dto.user;

import com.example.education_centre.dto.ValidationGroups;
import com.example.education_centre.model.Auditable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckUser extends Auditable {
    long id;
    @NotBlank(message = "không được bỏ trống mã người dùng",
            groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    String userCode;
    @NotBlank(message = "không được bỏ trống tên người dùng",
            groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    String name;
    @NotBlank(message = "không được bỏ trống email",
            groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    @Email(message = "email phải có định dạng: example@ttdt.com",
            groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    String email;
    @NotBlank(message = "không được bỏ trống mật khẩu", groups = ValidationGroups.OnCreate.class)
    @Size(min = 8, message = "mật khẩu phải chứa ít nhất 8 ký tự", groups = ValidationGroups.OnCreate.class)
    String password;
    @NotBlank(message = "không được bỏ trống phần nhập lại mật khẩu", groups = ValidationGroups.OnCreate.class)
    String confirmPassword;
    String phone;
    String address;
    String resultAddress;
    MultipartFile avatar;
    String avatarLink;
    @Column(nullable = false)
    Boolean status = true;
    @Column(nullable = false)
    Boolean gender;
    @Past(message = "Ngày sinh phải ở quá khứ",
            groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dob;
    int idRole;
}
