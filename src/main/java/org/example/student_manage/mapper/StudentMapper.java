package org.example.student_manage.mapper;

import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.entity.Student;

public class StudentMapper {
    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        return studentDTO;
    }


    public static Student toEntity(StudentDTOUI studentDTOUI) {
        Student student = new Student();
        student.setFirstName(studentDTOUI.getFirstName());
        student.setLastName(studentDTOUI.getLastName());
        student.setPassword(studentDTOUI.getPassword());
        student.setEmail(studentDTOUI.getEmail());
        return student;
    }
}
