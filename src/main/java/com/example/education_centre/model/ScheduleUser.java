package com.example.education_centre.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "attendance")
public class ScheduleUser{
    @EmbeddedId // Chỉ định đây là khóa chính kết hợp
    ScheduleUserId id;

    @ManyToOne
    @MapsId("userId") // Ánh xạ userId từ ClassUserId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @ManyToOne
    @MapsId("scheduleId") // Ánh xạ classId từ ClassUserId
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    Schedule schedule;

    @Column(nullable = false)
    Boolean checked;
}
