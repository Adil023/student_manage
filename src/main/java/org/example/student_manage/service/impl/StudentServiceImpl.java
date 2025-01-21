package org.example.student_manage.service.impl;

import org.example.student_manage.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentServiceImpl {

    ResponseEntity<List<Student>> getAllStudents();

    ResponseEntity<Student> getStudentsById(Long studentId);

    ResponseEntity<Student> createStudent(Student student);

    ResponseEntity<Student> updateStudent(Long studentId, Student student);

    void deleteStudent(Long studentId);
}
