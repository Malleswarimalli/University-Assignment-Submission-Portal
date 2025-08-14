package com.example.university.service;

import com.example.university.model.ClassDetailsDTO;
import com.example.university.model.User;
import com.example.university.repository.AssignmentRepository;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private AssignmentRepository assignmentRepository;

    // We reuse the StaffService to avoid duplicating code
    @Autowired
    private StaffService staffService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<ClassDetailsDTO> getAllClasses() {
        return staffService.getAllClassesWithStudents();
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        // 1. Find all students enrolled in this course
        List<User> studentsInCourse = userRepository.findByCourseId(courseId);
        
        // 2. Delete all those students
        userRepository.deleteAll(studentsInCourse);

        // 3. Delete the course itself. Associated assignments and submissions
        // should be deleted automatically if cascading is set up correctly.
        courseRepository.deleteById(courseId);
    }
}
