package org.example.student_manage.service;
import jakarta.transaction.Transactional;
import org.example.student_manage.dto.StudentDTO;
import org.example.student_manage.dto.StudentDTOUI;
import org.example.student_manage.entity.Student;
import org.example.student_manage.exception.StudentNotFoundException;
import org.example.student_manage.mapper.StudentMapper;
import org.example.student_manage.repository.StudentRepository;
import org.example.student_manage.service.impl.StudentServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements StudentServiceImpl {


    private StudentMapper studentMapper;

    private  StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


//    @Override
//    public ResponseEntity<List<StudentDTO>> getAllStudents() {
//        List<StudentDTO> allStudents = studentRepository.findAll()
//                .stream()
//                .map(StudentMapper::toStudentDTO)
//                .toList();
//       return new ResponseEntity<>(allStudents, HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<StudentDTO> getStudentsById(Long studentId) {
//       Student studentById = studentRepository.findById(studentId)
//               .orElseThrow(()->new StudentNotFoundException("Student not found"));
//
//        StudentDTO studentDTO = StudentMapper.toStudentDTO(studentById);
//       return new ResponseEntity<>(studentDTO,HttpStatus.OK);
//    }

    @Override
    @Transactional
    public ResponseEntity<StudentDTO> createStudent(StudentDTOUI studentDtoUi) {
        Student savedStudent = studentMapper.toEntity(studentDtoUi);
        Student newStudent =  studentRepository.save(savedStudent);
        StudentDTO studentDTO =studentMapper.toStudentDTO(newStudent);
        return new ResponseEntity<>(studentDTO,HttpStatus.CREATED);
    }

//    @Override
//    @Transactional
//    public ResponseEntity<StudentDTO> updateStudent(Long studentId, StudentDTOUI student) {
//       Student entityStudent =  studentRepository.findById(studentId)
//               .orElseThrow(() ->new StudentNotFoundException("Student not found with "+studentId));
//            BeanUtils.copyProperties(student, entityStudent);
//            Student updatedStudent = studentRepository.save(entityStudent);
//            StudentDTO studentDTO =  StudentMapper.toStudentDTO(updatedStudent);
//            return new ResponseEntity<>(studentDTO,HttpStatus.OK);
//
//    }
//
//
//    @Override
//    @Transactional
//    public void deleteStudent(Long studentId) {
//        studentRepository.deleteById(studentId);
//    }
}
