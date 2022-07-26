package com.demo.repository;

import com.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
    
    String getByName(String name);

    @Query(nativeQuery = true, value = "select s.phone from Student s where s.phone = ?1")
    String getByPhone(String phone);
}
