package com.demo.controller;

import com.demo.entity.Student;
import com.demo.service.IStudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management/api/v1/student")
public class StudentManagementController {

    private final IStudentService studentService;

    @Autowired
    public StudentManagementController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "Save Student")
    @PostMapping
    public ResponseEntity<Student> saveStudent(@ApiParam(value = "Student Body should be provided")
                                               @RequestBody Student student) {
        if (studentService.add(student))
            return new ResponseEntity<>(HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Update Student With specific ID")
    @PutMapping("/id/{id}")
    public ResponseEntity<Student> updateStudent(
            @ApiParam(value = "Update desired Student parameter (ID must be provided)")
            @PathVariable Long id, Student student) {
        if (studentService.update(id, student))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Save Student With specific ID")
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        if (studentService.getStudentById(id) != null) {
            studentService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
