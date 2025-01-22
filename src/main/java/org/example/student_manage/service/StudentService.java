package org.example.student_manage.service;
import jakarta.transaction.Transactional;
import org.example.student_manage.dto.StudentDTO;
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


    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> allStudents = studentRepository.findAll()
                .stream()
                .map(StudentMapper::toStudentDTO)
                .toList();
       return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    public ResponseEntity<Student> getStudentsById(Long studentId) {
       Student studentById = studentRepository.findById(studentId)
               .orElseThrow(()->new StudentNotFoundException("Student not found"));

       return new ResponseEntity<>(studentById,HttpStatus.OK);
    }

    public ResponseEntity<Student> createStudent(Student student) {
        Student newStudent =  studentRepository.save(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    public ResponseEntity<Student> updateStudent(Long studentId, Student student) {
        Optional<Student> updatedStudent = studentRepository.findById(studentId);
        if(updatedStudent.isPresent()) {
            student.setFirstName(updatedStudent.get().getFirstName());
            student.setLastName(updatedStudent.get().getLastName());
            student.setPassword(updatedStudent.get().getPassword());
            student.setEmail(updatedStudent.get().getEmail());
            return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
        }throw new StudentNotFoundException("Student has not found");

    }


    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
