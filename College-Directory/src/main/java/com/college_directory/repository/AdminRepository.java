package com.college_directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college_directory.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
