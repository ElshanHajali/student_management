package com.demo.service;

import com.demo.entity.Student;

import java.util.List;

public interface IStudentService {

    boolean add(Student student);

    boolean update(Long id, Student student);

    List<Student> getStudentList();

    Student getStudentById(long id);


    void delete(Long id);
}
