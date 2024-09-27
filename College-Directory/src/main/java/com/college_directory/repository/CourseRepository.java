package com.college_directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college_directory.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
