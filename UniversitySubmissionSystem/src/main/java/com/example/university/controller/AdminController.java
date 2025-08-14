package com.example.university.controller;

import com.example.university.model.ClassDetailsDTO;
import com.example.university.model.User;
import com.example.university.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/courses")
    public ResponseEntity<List<ClassDetailsDTO>> getAllClasses() {
        List<ClassDetailsDTO> classes = adminService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        adminService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }
}
