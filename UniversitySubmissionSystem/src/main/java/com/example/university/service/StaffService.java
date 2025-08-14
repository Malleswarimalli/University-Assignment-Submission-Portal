package com.example.university.service;

import com.example.university.model.*;
import com.example.university.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffService {

    @Autowired private CourseRepository courseRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AssignmentRepository assignmentRepository;
    @Autowired private SubmissionRepository submissionRepository;
    
    @Transactional
    public void addStudentsToClass(Long courseId, List<StudentDTO> studentsToAdd) {
        // First, ensure the course actually exists
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalStateException("Course not found with id: " + courseId);
        }

        for (StudentDTO studentDto : studentsToAdd) {
            // Check for duplicate emails to prevent errors
            if (userRepository.findByEmail(studentDto.getEmail()).isPresent()) {
                throw new IllegalStateException("A user with email " + studentDto.getEmail() + " already exists.");
            }

            User newStudent = new User();
            newStudent.setUsername(studentDto.getName());
            newStudent.setEmail(studentDto.getEmail());
            newStudent.setRegisterNo(studentDto.getRegisterNo());
            if (studentDto.getDob() != null && !studentDto.getDob().isEmpty()) {
                newStudent.setDob(LocalDate.parse(studentDto.getDob()));
            }
            newStudent.setRole("STUDENT");
            newStudent.setPassword("defaultPassword123");
            newStudent.setCourseId(courseId); // Link the new student to the existing course
            userRepository.save(newStudent);
        }
    }

    @Transactional
    public Course createClassWithStudents(CreateClassRequest request) {
        Course newCourse = new Course();
        newCourse.setName(request.getClassName());
        Course savedCourse = courseRepository.save(newCourse);

        for (StudentDTO studentDto : request.getStudents()) {
            if (userRepository.findByEmail(studentDto.getEmail()).isPresent()) {
                throw new IllegalStateException("A user with email " + studentDto.getEmail() + " already exists.");
            }
            User newStudent = new User();
            newStudent.setUsername(studentDto.getName());
            newStudent.setEmail(studentDto.getEmail());
            newStudent.setRegisterNo(studentDto.getRegisterNo());
            if (studentDto.getDob() != null && !studentDto.getDob().isEmpty()) {
                newStudent.setDob(LocalDate.parse(studentDto.getDob()));
            }
            newStudent.setRole("STUDENT");
            newStudent.setPassword("defaultPassword123");
            newStudent.setCourseId(savedCourse.getId());
            userRepository.save(newStudent);
        }
        return savedCourse;
    }
    
    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public List<ClassDetailsDTO> getAllClassesWithStudents() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> {
            List<User> students = userRepository.findByCourseId(course.getId());
            List<StudentDTO> studentDTOs = students.stream().map(student -> {
                StudentDTO dto = new StudentDTO();
                dto.setName(student.getUsername());
                dto.setRegisterNo(student.getRegisterNo());
                dto.setEmail(student.getEmail());
                return dto;
            }).collect(Collectors.toList());
            ClassDetailsDTO classDetails = new ClassDetailsDTO();
            classDetails.setCourseId(course.getId());
            classDetails.setCourseName(course.getName());
            classDetails.setStudents(studentDTOs);
            return classDetails;
        }).collect(Collectors.toList());
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public List<SubmissionStatusDTO> getSubmissionStatusForAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new IllegalStateException("Assignment not found with id: " + assignmentId));

        List<User> studentsInCourse = userRepository.findByCourseId(assignment.getCourseId());

        return studentsInCourse.stream().map(student -> {
            Optional<Submission> submissionOpt = submissionRepository.findByStudentIdAndAssignmentId(student.getId(), assignmentId);
            SubmissionStatusDTO statusDTO = new SubmissionStatusDTO();
            statusDTO.setStudentId(student.getId());
            statusDTO.setStudentName(student.getUsername());
            statusDTO.setRegisterNo(student.getRegisterNo());

            if (submissionOpt.isPresent()) {
                Submission submission = submissionOpt.get();
                statusDTO.setSubmissionId(submission.getId());
                statusDTO.setGrade(submission.getGrade());
                statusDTO.setStatus(submission.getGrade() != null ? "Graded" : "Submitted");
                statusDTO.setAnswerText(submission.getFileUrl());
            } else {
                statusDTO.setStatus("Not Submitted");
            }
            return statusDTO;
        }).collect(Collectors.toList());
    }

    public Submission gradeSubmission(Long submissionId, String grade) {
        Submission submission = submissionRepository.findById(submissionId)
            .orElseThrow(() -> new IllegalStateException("Submission not found with id: " + submissionId));
        submission.setGrade(grade);
        return submissionRepository.save(submission);
    }
}
