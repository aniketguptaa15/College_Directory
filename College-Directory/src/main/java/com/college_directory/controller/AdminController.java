package com.college_directory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Import this for authorization
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college_directory.entity.Admin;
import com.college_directory.services.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Only allow access if the user has the ADMIN role
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id") // Allow access if user is ADMIN or the specific user
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer userId) {
        Optional<Admin> admin = adminService.getAdminById(userId);
        return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Restrict to ADMIN role
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id") // Allow if ADMIN or the specific user
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer userId, @RequestBody Admin adminDetails) {
        Optional<Admin> adminOptional = adminService.getAdminById(userId);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setPhoto(adminDetails.getPhoto());
            admin.setDepartment(adminDetails.getDepartment());
            return ResponseEntity.ok(adminService.saveAdmin(admin));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')") // Restrict to ADMIN role
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer userId) {
        Optional<Admin> adminOptional = adminService.getAdminById(userId);

        if (adminOptional.isPresent()) {
            adminService.deleteAdmin(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
