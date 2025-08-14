package com.example.university.service;

import com.example.university.model.*;
import com.example.university.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired private UserRepository userRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private AssignmentRepository assignmentRepository;
    @Autowired private SubmissionRepository submissionRepository;

    public StudentProfileDTO getStudentProfile(Long studentId) {
        User student = userRepository.findById(studentId)
            .orElseThrow(() -> new IllegalStateException("Student not found with id: " + studentId));
        
        Course course = courseRepository.findById(student.getCourseId())
            .orElseThrow(() -> new IllegalStateException("Course not found for student"));

        StudentProfileDTO profile = new StudentProfileDTO();
        profile.setStudentName(student.getUsername());
        profile.setEmail(student.getEmail());
        profile.setRegisterNo(student.getRegisterNo());
        profile.setDob(student.getDob());
        profile.setCourseName(course.getName());
        profile.setCourseId(course.getId());
        return profile;
    }

    public List<StudentAssignmentDTO> getAssignmentsForStudent(Long studentId, Long courseId) {
        List<Assignment> assignments = assignmentRepository.findAllByCourseId(courseId);

        return assignments.stream().map(assignment -> {
            Optional<Submission> submissionOpt = submissionRepository.findByStudentIdAndAssignmentId(studentId, assignment.getId());
            StudentAssignmentDTO dto = new StudentAssignmentDTO();
            dto.setAssignmentId(assignment.getId());
            dto.setTitle(assignment.getTitle());
            dto.setDescription(assignment.getDescription());
            dto.setDueDate(assignment.getDueDate());

            if (submissionOpt.isPresent()) {
                Submission submission = submissionOpt.get();
                dto.setGrade(submission.getGrade());
                dto.setStatus(submission.getGrade() != null ? "Graded" : "Submitted");
            } else {
                dto.setStatus("Not Submitted");
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public Submission createOrUpdateSubmission(Submission submission) {
        Optional<Submission> existing = submissionRepository.findByStudentIdAndAssignmentId(
            submission.getStudentId(), submission.getAssignmentId());

        if (existing.isPresent()) {
            Submission updatedSubmission = existing.get();
            updatedSubmission.setFileUrl(submission.getFileUrl()); // Assuming fileUrl contains the answer
            return submissionRepository.save(updatedSubmission);
        } else {
            return submissionRepository.save(submission);
        }
    }
}
