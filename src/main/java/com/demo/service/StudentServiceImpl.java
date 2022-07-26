package com.demo.service;

import com.demo.entity.Student;
import com.demo.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements IStudentService {

    private final IStudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean add(Student student) {
        if (!student.getName().equalsIgnoreCase(studentRepository.getByName(student.getName())) &&
                !student.getPhone().equalsIgnoreCase(studentRepository.getByPhone(student.getPhone()))) {
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Student student) {
        Student studentFromDB = studentRepository.getById(id);
        if (id.equals(studentFromDB.getId())) {
            if (Objects.nonNull(student.getName()) &&
                    !"".equalsIgnoreCase(student.getName())) {
                studentFromDB.setName(student.getName());
            }
            if (Objects.nonNull(student.getPhone()) &&
                    !"".equalsIgnoreCase(student.getPhone())) {
                studentFromDB.setPhone(student.getPhone());
            }
            studentRepository.save(studentFromDB);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
