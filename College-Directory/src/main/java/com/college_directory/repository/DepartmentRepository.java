package com.college_directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college_directory.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
