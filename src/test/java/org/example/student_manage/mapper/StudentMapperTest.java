package org.example.student_manage.mapper;

import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.entity.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentMapperTest {

    @Test
    public void toTeststudentDTO(){
        // given
        Student student = new Student();
        student.setFirstName("Adil");
        student.setLastName("Gadirov");
        //when
        StudentDTO studentDTO = StudentMapper.toStudentDTO(student);
        //assert or actual

        assertNotNull(studentDTO);
        assertEquals("Adil", studentDTO.getFirstName());
        assertEquals("Gadirov", studentDTO.getLastName());
    }

    @Test
    public void testToEntity(){
        // given
        StudentDTOUI studentDTOUI = new StudentDTOUI();
        studentDTOUI.setFirstName("Adil");
        studentDTOUI.setLastName("Gadirov");
        studentDTOUI.setPassword("pasdos");
        studentDTOUI.setEmail("adil.gadirov@gmail.com");
        // when
        Student student = StudentMapper.toEntity(studentDTOUI);

        // actual or assertion
        assertNotNull(student);
        assertEquals("Adil", student.getFirstName());
        assertEquals("Gadirov", student.getLastName());
        assertEquals("pasdos", student.getPassword());
        assertEquals("adil.gadirov@gmail.com", student.getEmail());
    }

}
