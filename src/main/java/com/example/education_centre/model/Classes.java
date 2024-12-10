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
public class Classes extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "class_code", unique = true, nullable = false)
    String classCode;
    @Column(nullable = false)
    String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Schedule> schedules;

    @ToString.Exclude
    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<ClassUser> classUsers;
}
