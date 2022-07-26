package com.demo.controller;

import com.demo.entity.Student;
import com.demo.service.IStudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "Get All Students")
    @GetMapping
    public List<Student> getStudentList() {
        return studentService.getStudentList();
    }

    @ApiOperation(value = "Get specific Student")
    @GetMapping("/id/{id}")
    public Student getStudentById(@PathVariable("id") Long studentId) {
        return studentService.getStudentById(studentId);
    }

}



























