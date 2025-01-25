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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

       verify(studentMapper,times(1)).toEntity(studentDTOUI);
       verify(studentRepository,times(1)).save(student);
       verify(studentMapper,times(1)).toStudentDTO(student);
    }

    @Test
    public void should_find_all_students_successfully() {

        Student student1 = new Student(1L,
                "Adil",
                "Gadirov",
                "adil@gmail.com",
                "adil12345");

        Student student2 = new Student(2L,
                "Samir",
                "Bagirov",
                "samir@gmail.com",
                "samir12345");

       List<Student> listStudent = Arrays.asList(student1,student2);
        StudentDTO studentDTO1 = new StudentDTO(
                "Adil",
                "Gadirov"
        );

        StudentDTO studentDTO2 = new StudentDTO(
                "Samir",
                "Bagirov"
        );

        when(studentRepository.findAll())
                .thenReturn(listStudent);

        when(studentMapper.toStudentDTO(student1))
                .thenReturn(studentDTO1);

        when(studentMapper.toStudentDTO(student2))
                .thenReturn(studentDTO2);

        ResponseEntity<List<StudentDTO>> response = studentService.getAllStudents();



        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(2,response.getBody().size());
        assertEquals("Adil",response.getBody().get(0).getFirstName() );
        assertEquals("Samir",response.getBody().get(1).getFirstName() );

        verify(studentRepository,times(1)).findAll();
        verify(studentMapper,times(1)).toStudentDTO(student1);
        verify(studentMapper,times(1)).toStudentDTO(student2);

    }

    @Test
    public void should_find_student_by_id_successfully() {
        StudentDTOUI studentDTOUI = new StudentDTOUI(
                1L,
                "Adil",
                "Gadirov",
                "pasdos",
                "adil@gmail.com"
        );

        Student student = new Student(
                1L,
                "Adil",
                "Gadirov",
                "pasdos",
                "adil@gmail.com"
        );



        StudentDTO studentDTO = new StudentDTO(
                "Adil",
                "Gadirov"
        );

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(studentMapper.toStudentDTO(student))
                .thenReturn(studentDTO);

         ResponseEntity<StudentDTO> response =   studentService.getStudentsById(1L);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("Adil",response.getBody().getFirstName());
        verify(studentRepository,times(1)).findById(1L);
        verify(studentMapper,times(1)).toStudentDTO(student);
    }
}

