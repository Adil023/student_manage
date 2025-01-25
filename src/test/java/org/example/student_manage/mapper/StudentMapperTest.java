package org.example.student_manage.mapper;

import org.assertj.core.api.Assertions;
import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.entity.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentMapperTest {

    private StudentMapper studentMapper;

    @Test
    public void toTeststudentDTO(){

        Student student = new Student();
        student.setFirstName("Adil");
        student.setLastName("Gadirov");

        StudentDTO studentDTO = studentMapper.toStudentDTO(student);

        assertNotNull(studentDTO);
        assertEquals("Adil", studentDTO.getFirstName());
        assertEquals("Gadirov", studentDTO.getLastName());
    }

    @Test
    public void testToEntity(){

        StudentDTOUI studentDTOUI = new StudentDTOUI();

        studentDTOUI.setFirstName("Adil");
        studentDTOUI.setLastName("Gadirov");
        studentDTOUI.setPassword("pasdos");
        studentDTOUI.setEmail("adil.gadirov@gmail.com");

        Student expected = new Student();

        expected.setFirstName("Adil");
        expected.setLastName("Gadirov");
        expected.setPassword("pasdos");
        expected.setEmail("adil.gadirov@gmail.com");


        Student actual = studentMapper.toEntity(studentDTOUI);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}
