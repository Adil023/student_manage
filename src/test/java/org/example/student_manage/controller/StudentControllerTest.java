package org.example.student_manage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_create_student_successfully() throws Exception {
        // input
        StudentDTOUI studentDTOUI = new StudentDTOUI(
                1L,
                "Adil",
                "Gadirov",
                "adil@mail.ru",
                "pasdos"
        );

        // output
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("Adil");
        studentDTO.setLastName("Gadirov");

        Mockito.when(studentService.createStudent(any(StudentDTOUI.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED)
                        .body(studentDTO)); // Return directly the DTO, not ResponseEntity

        mockMvc.perform(post("/api/v1/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(studentDTOUI)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(studentDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(studentDTO.getLastName()));

        Mockito.verify(studentService).createStudent(any(StudentDTOUI.class));
    }

    @Test
    public void should_get_all_students_successfully() throws Exception {
        StudentDTO studentDTO1 = new StudentDTO(
                "Adil",
                "Gadirov"
        );

        StudentDTO studentDTO2 = new StudentDTO(
                "Adil",
                "Gadirov"
        );

        List<StudentDTO> studentDTOList = Arrays.asList(studentDTO1, studentDTO2);

        Mockito.when(studentService.getAllStudents())
                .thenReturn(ResponseEntity.ok(studentDTOList));

        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(studentDTO1.getFirstName()));

    }
}
