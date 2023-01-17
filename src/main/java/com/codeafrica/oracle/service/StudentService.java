package com.codeafrica.oracle.service;


import com.codeafrica.oracle.dto.StudentDto;
import com.codeafrica.oracle.models.Student;

import java.util.List;

public interface StudentService {


    Student createStudent(StudentDto studentDto);

    List<Student> getAllStudents();

    Student findStudentById(Integer id);


    void deleteStudent(Integer studentId);

    Student updateStudent(Integer studentId, StudentDto studentDto);
}
