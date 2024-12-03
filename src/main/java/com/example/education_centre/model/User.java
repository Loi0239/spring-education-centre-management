package com.example.education_centre.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "user_code", nullable = false, unique = true)
    String userCode;
    @Column(nullable = false)
    String name;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    String phone;
    String address;
    String avatar;
    @Column(nullable = false)
    Boolean status;
    @Column(nullable = false)
    Boolean gender;
    LocalDate dob;
//    @CreatedDate
//    @Column(name="created_at", nullable = false, updatable = false)
//    LocalDateTime createdAt;
//    @LastModifiedDate
//    @Column(name="update_at")
//    LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_role", referencedColumnName = "id")
    Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<ClassUser> classUsers;
}