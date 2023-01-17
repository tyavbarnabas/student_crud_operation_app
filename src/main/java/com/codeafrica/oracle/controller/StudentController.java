package com.codeafrica.oracle.controller;

import com.codeafrica.oracle.dto.StudentDto;
import com.codeafrica.oracle.models.Student;
import com.codeafrica.oracle.response.StudentResponse;
import com.codeafrica.oracle.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PostMapping("/create")
    public ResponseEntity<StudentResponse<?>> createStudent(@RequestBody StudentDto studentDto){
       try{
           Student student = studentService.createStudent(studentDto);
           return new ResponseEntity<>(new StudentResponse<>(true, "student with the  name " + student.getFirstName() + " "
                   + student.getLastName() + " was created successfully ", studentDto), HttpStatus.CREATED);
       }catch (Exception e){
                    return new ResponseEntity<>(new StudentResponse<>(false, "student was not created ", " try again"), HttpStatus.EXPECTATION_FAILED);
       }
    }



    @GetMapping("/allStudents")
    public ResponseEntity<StudentResponse<?>> getAllStudent(){
        try{
                List<Student> allStudents = studentService.getAllStudents();
                return new ResponseEntity<>(new StudentResponse<>(true ,"student list was gotten successfully", allStudents),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new StudentResponse<>(false , "could not get the list of student",null),HttpStatus.EXPECTATION_FAILED);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse<?>>getStudentById(@PathVariable("id") Integer id){
        try{
            Student student = studentService.findStudentById(id);
            return  new ResponseEntity<>(new StudentResponse<>(true ,"student with id " + student.getId() +
                    " was found successfully " ,student),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(new StudentResponse<>(false, "student with  could not not be found ", null ),HttpStatus.EXPECTATION_FAILED);
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentResponse<?>> deleteStudent(@PathVariable("id") Integer studentId){
        try{
             studentService.deleteStudent(studentId);
            return new ResponseEntity<>(new StudentResponse<>(true ,"student with id " +
                 studentId + "has been deleted",null),HttpStatus.OK );
        }catch (Exception e){
            return  new ResponseEntity<>(new StudentResponse<>(false,"student not deleted",null),HttpStatus.EXPECTATION_FAILED);
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse<?>> updateUser(@PathVariable("id") Integer studentId, @RequestBody StudentDto  studentDto){
        try{
            Student student = studentService.updateStudent(studentId, studentDto);
            return new ResponseEntity<>(new StudentResponse<>(true ,"The student " + student.getFirstName() + student.getLastName() +
                    "has been updated",null ), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new StudentResponse<>(false, "student was not updated",null),HttpStatus.EXPECTATION_FAILED);
        }

    }



}
