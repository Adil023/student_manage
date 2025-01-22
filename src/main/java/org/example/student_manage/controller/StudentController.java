package org.example.student_manage.controller;

import jakarta.validation.Valid;
import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.entity.Student;
import org.example.student_manage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class StudentController {

    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
       return  studentService.getAllStudents();
    }

    @GetMapping("students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
        return studentService.getStudentsById(studentId);
    }

    @PostMapping("new")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTOUI student){
        return studentService.createStudent(student);
    }

    @PutMapping("edit/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTOUI student){
        return studentService.updateStudent(studentId,student);
    }

    @DeleteMapping("delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    };

}
