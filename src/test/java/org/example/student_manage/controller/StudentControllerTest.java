package org.example.student_manage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    @SuppressWarnings("deprecation")
    @MockBean
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void createStudent() throws Exception {
        var request = new StudentDTOUI();
        request.setFirstName("Adil");
        request.setLastName("Gadirov");
        request.setEmail("adil@mail.ru");
        request.setPassword("adil12345");

        doNothing().when(studentService).createStudent(request);

        mockMvc.perform(post("/api/v1/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
