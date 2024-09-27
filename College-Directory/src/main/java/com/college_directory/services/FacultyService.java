package com.college_directory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college_directory.entity.Faculty;
import com.college_directory.repository.FacultyRepository;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        return optionalFaculty.orElse(null);
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Integer id, Faculty faculty) {
        Optional<Faculty> existingFaculty = facultyRepository.findById(id);
        if (existingFaculty.isPresent()) {
            Faculty updatedFaculty = existingFaculty.get();
            updatedFaculty.setPhoto(faculty.getPhoto());
            updatedFaculty.setDepartment(faculty.getDepartment());
            updatedFaculty.setOfficeHours(faculty.getOfficeHours());
            return facultyRepository.save(updatedFaculty);
        }
        return null;
    }

    public void deleteFaculty(Integer id) {
        facultyRepository.deleteById(id);
    }
}
