package com.example.education_centre.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "administrative_regions")
public class AdministrativeRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    @Column(name = "name_en")
    String nameEn;
    @Column(name = "code_name")
    String codeName;
    @Column(name = "code_name_en")
    String codeNameEn;
}
