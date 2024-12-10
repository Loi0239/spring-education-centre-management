package com.example.education_centre.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "class_user")
public class ClassUser extends Auditable {
    @EmbeddedId // Chỉ định đây là khóa chính kết hợp
    private ClassUserId id;

    @ManyToOne
    @MapsId("userId") // Ánh xạ userId từ ClassUserId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @ManyToOne
    @MapsId("classId") // Ánh xạ classId từ ClassUserId
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    Classes classes;

    @Column(name = "presence_point")
    double presencePoint;
    double test1;
    double test2;
    @Column(name = "mid_test")
    double midTest;
    @Column(name = "final_test")
    double finalTest;
}
