package com.college_directory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college_directory.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
