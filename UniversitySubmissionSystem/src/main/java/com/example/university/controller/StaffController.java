package com.example.university.controller;

import com.example.university.model.AddStudentsRequest; // Make sure this import is present
import com.example.university.model.Assignment;
import com.example.university.model.ClassDetailsDTO;
import com.example.university.model.Course;
import com.example.university.model.CreateClassRequest;
import com.example.university.model.GradeSubmissionRequest;
import com.example.university.model.Submission;
import com.example.university.model.SubmissionStatusDTO;
import com.example.university.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/create-class")
    public ResponseEntity<Course> createClassWithStudents(@RequestBody CreateClassRequest request) {
        Course newCourse = staffService.createClassWithStudents(request);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }
    
    // This is the endpoint that was missing or incorrect
    @PostMapping("/classes/{courseId}/students")
    public ResponseEntity<?> addStudentsToClass(@PathVariable Long courseId, @RequestBody AddStudentsRequest request) {
        staffService.addStudentsToClass(courseId, request.getStudents());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/classes")
    public ResponseEntity<List<ClassDetailsDTO>> getAllClasses() {
        List<ClassDetailsDTO> classes = staffService.getAllClassesWithStudents();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PostMapping("/assignments")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment newAssignment = staffService.createAssignment(assignment);
        return new ResponseEntity<>(newAssignment, HttpStatus.CREATED);
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        List<Assignment> assignments = staffService.getAllAssignments();
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @GetMapping("/assignments/{assignmentId}/submissions")
    public ResponseEntity<List<SubmissionStatusDTO>> getSubmissionStatuses(@PathVariable Long assignmentId) {
        List<SubmissionStatusDTO> statuses = staffService.getSubmissionStatusForAssignment(assignmentId);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @PutMapping("/submissions/{submissionId}/grade")
    public ResponseEntity<Submission> gradeSubmission(@PathVariable Long submissionId, @RequestBody GradeSubmissionRequest request) {
        Submission gradedSubmission = staffService.gradeSubmission(submissionId, request.getGrade());
        return new ResponseEntity<>(gradedSubmission, HttpStatus.OK);
    }
}
