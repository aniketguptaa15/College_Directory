package com.college_directory.dto;

public class LoginResponse {
    private String token;
    private String role;

    // Default constructor
    public LoginResponse() {
    }

    // Parameterized constructor
    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
