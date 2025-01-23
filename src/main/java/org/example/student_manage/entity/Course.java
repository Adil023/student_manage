package org.example.student_manage.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @Column(name="course_name")
    String courseName;
    @Column(name="deparment_name")
    String department;

    @ManyToMany(
            mappedBy = "students"
    )

    List<Student> students = new ArrayList<>();

}
