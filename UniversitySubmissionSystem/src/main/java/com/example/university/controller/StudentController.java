package com.example.university.controller;

import com.example.university.model.StudentAssignmentDTO;
import com.example.university.model.StudentProfileDTO;
import com.example.university.model.Submission;
import com.example.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}/profile")
    public ResponseEntity<StudentProfileDTO> getStudentProfile(@PathVariable Long studentId) {
        StudentProfileDTO profile = studentService.getStudentProfile(studentId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/{studentId}/assignments")
    public ResponseEntity<List<StudentAssignmentDTO>> getAssignments(@PathVariable Long studentId) {
        StudentProfileDTO profile = studentService.getStudentProfile(studentId);
        List<StudentAssignmentDTO> assignments = studentService.getAssignmentsForStudent(studentId, profile.getCourseId());
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @PostMapping("/submissions")
    public ResponseEntity<Submission> submitAssignment(@RequestBody Submission submission) {
        Submission savedSubmission = studentService.createOrUpdateSubmission(submission);
        return new ResponseEntity<>(savedSubmission, HttpStatus.CREATED);
    }
}
