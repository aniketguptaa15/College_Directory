package com.college_directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college_directory.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // You can define custom query methods here if needed
}
