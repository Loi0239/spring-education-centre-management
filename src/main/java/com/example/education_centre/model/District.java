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
@Table(name = "districts")
public class District {
    @Id
    @Column(length = 20)
    String code;

    String name;
    @Column(name = "name_en")
    String nameEn;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "full_name_en")
    String fullNameEn;
    @Column(name = "code_name")
    String codeName;

    @ManyToOne
    @JoinColumn(name = "province_code", referencedColumnName = "code")
    Province province;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id", referencedColumnName = "id")
    AdministrativeUnit administrativeUnit;
}