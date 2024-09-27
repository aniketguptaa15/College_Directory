package com.college_directory.dto;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private String role; // Can be "STUDENT", "FACULTY_MEMBER", "ADMINISTRATOR"

    // Default constructor
    public LoginRequest() {
    }

    // Parameterized constructor
    public LoginRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
