package com.example.education_centre.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false)
    LocalDate date;
    @Column(name = "time_begin", nullable = false)
    LocalTime timeBegin;
    @Column(name = "time_end", nullable = false)
    LocalTime timeEnd;
    @Column(nullable = false)
    String lesson;
    @Column(name = "class_room", nullable = false)
    String classRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    Classes classes;
}

