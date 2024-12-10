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
public class GroupPermission extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true, nullable = false)
    String name;
    @Column(name = "display_name", unique = true, nullable = false)
    String displayName;
    String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "groupPermission", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Permission> permissions;
}
