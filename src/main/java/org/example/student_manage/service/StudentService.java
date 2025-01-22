package org.example.student_manage.service;
import jakarta.transaction.Transactional;
import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.entity.Student;
import org.example.student_manage.exception.StudentNotFoundException;
import org.example.student_manage.mapper.StudentMapper;
import org.example.student_manage.repository.StudentRepository;
import org.example.student_manage.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements StudentServiceImpl {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    @Override
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> allStudents = studentRepository.findAll()
                .stream()
                .map(StudentMapper::toStudentDTO)
                .toList();
       return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Student> getStudentsById(Long studentId) {
       Student studentById = studentRepository.findById(studentId)
               .orElseThrow(()->new StudentNotFoundException("Student not found"));

       return new ResponseEntity<>(studentById,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentDTO> createStudent(StudentDTOUI studentDtoUi) {
        Student savedStudent = StudentMapper.toEntity(studentDtoUi);
        Student newStudent =  studentRepository.save(savedStudent);
        StudentDTO studentDTO =StudentMapper.toStudentDTO(newStudent);
        return new ResponseEntity<>(studentDTO,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StudentDTO> updateStudent(Long studentId, StudentDTOUI student) {
       Student entityStudent =  studentRepository.findById(studentId)
               .orElseThrow(() ->new StudentNotFoundException("Student not found with "+studentId));
            entityStudent.setFirstName(student.getFirstName());
            entityStudent.setLastName(student.getLastName());
            entityStudent.setPassword(student.getPassword());
            entityStudent.setEmail(student.getEmail());
            Student updatedStudent = studentRepository.save(entityStudent);
            StudentDTO studentDTO =  StudentMapper.toStudentDTO(updatedStudent);
            return new ResponseEntity<>(studentDTO,HttpStatus.OK);

    }


    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
