package com.codeafrica.oracle.serviceImpl;

import com.codeafrica.oracle.dto.StudentDto;
import com.codeafrica.oracle.models.Student;
import com.codeafrica.oracle.repository.StudentRepository;
import com.codeafrica.oracle.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Override
    public Student createStudent(StudentDto studentDto) {
        Optional<Student > student = studentRepository.findByEmail(studentDto.getEmail());
        if(student.isPresent()){
            throw new IllegalStateException("student already exist in database");
        }
        Student newStudent = new Student();
        BeanUtils.copyProperties(studentDto,newStudent);
        return studentRepository.save(newStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student>allStudents = studentRepository.findAll();
        return allStudents.stream().map(std -> new Student(std.getId(), std.getFirstName(),
                std.getLastName(), std.getEmail())).toList();
    }

    @Override
    public Student findStudentById(Integer id) {
        Student student = studentRepository.findById(id).get();
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student,studentDto);
        return student;
    }

    @Override
    public void deleteStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId).get();
        studentRepository.delete(student);
    }

    @Override
    public Student updateStudent(Integer studentId, StudentDto studentDto) {
        Student student = studentRepository.findById(studentId).get();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        return studentRepository.save(student);
    }


}
