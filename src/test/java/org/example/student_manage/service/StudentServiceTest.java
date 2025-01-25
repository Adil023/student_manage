package org.example.student_manage.service;

import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.entity.Student;
import org.example.student_manage.mapper.StudentMapper;
import org.example.student_manage.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_create_student_successfully() {
        // given
        StudentDTOUI studentDTOUI = new StudentDTOUI(
                1L,
                "Adil",
                "Gadirov",
                "adil@gmail.com",
                "pasdos");

        Student student = new Student(

                1L,
                "Adil",
                "Gadirov",
                "pasdos",
                "adil@gmail.com");

        StudentDTO studentDTO = new StudentDTO(
                "Adil",
                "Gadirov");

        when(studentMapper.toEntity(studentDTOUI))
                .thenReturn(student);

        when(studentRepository.save(student))
                .thenReturn(student);

        when(studentMapper.toStudentDTO(student))
                .thenReturn(studentDTO);


        //when
       ResponseEntity<StudentDTO> s = studentService.createStudent(studentDTOUI);
       assertNotNull(s);
       assertEquals(studentDTO.getFirstName(), s.getBody().getFirstName());
       assertEquals(studentDTO.getLastName(), s.getBody().getLastName());
    }
}