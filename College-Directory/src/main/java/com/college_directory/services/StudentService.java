package com.college_directory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.college_directory.entity.Student;
import com.college_directory.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setPhoto(updatedStudent.getPhoto());
                    student.setDepartment(updatedStudent.getDepartment());
                    student.setYear(updatedStudent.getYear());
                    return studentRepository.save(student);
                });
    }

    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
