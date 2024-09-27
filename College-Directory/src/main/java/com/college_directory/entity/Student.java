package com.college_directory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student") // Table name in the database
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // This field will act as the primary key and foreign key to User

    @Column
    private String photo; // URL or path to the student's profile photo

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department; // Foreign key linking to the Department entity

    @Column(nullable = false)
    private String year; // Year of study (e.g., Freshman, Sophomore, etc.)

    // Constructors
    public Student() {
    }

    public Student(Long userId, String photo, Department department, String year) {
        this.userId = userId;
        this.photo = photo;
        this.department = department;
        this.year = year;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userId=" + userId +
                ", photo='" + photo + '\'' +
                ", department=" + department +
                ", year='" + year + '\'' +
                '}';
    }
}
