package com.example.university.model;

import java.util.List;

// This DTO will hold a course and its list of students
public class ClassDetailsDTO {
    private Long courseId;
    private String courseName;
    private List<StudentDTO> students;

    // Getters and Setters
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public List<StudentDTO> getStudents() { return students; }
    public void setStudents(List<StudentDTO> students) { this.students = students; }
}
