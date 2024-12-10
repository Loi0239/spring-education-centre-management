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
@Table(name = "provinces")
public class Province {
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
    @JoinColumn(name = "administrative_unit_id", referencedColumnName = "id")
    AdministrativeUnit administrativeUnit;

    @ManyToOne
    @JoinColumn(name = "administrative_region_id", referencedColumnName = "id")
    AdministrativeRegion administrativeRegion;
}
