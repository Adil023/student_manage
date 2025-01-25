package org.example.student_manage.service.impl;

import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentServiceImpl {

//    ResponseEntity<List<StudentDTO>> getAllStudents();
//
//    ResponseEntity<StudentDTO> getStudentsById(Long studentId);

    ResponseEntity<StudentDTO> createStudent(StudentDTOUI studentdtoui);

//    ResponseEntity<StudentDTO> updateStudent(Long studentId, StudentDTOUI student);
//
//    void deleteStudent(Long studentId);
}
