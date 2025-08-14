    package com.example.university.model;

    import java.util.List;

    // This DTO represents the entire request: a class name and a list of students
    public class CreateClassRequest {
        private String className;
        private List<StudentDTO> students;

        // Getters and Setters
        public String getClassName() { return className; }
        public void setClassName(String className) { this.className = className; }
        public List<StudentDTO> getStudents() { return students; }
        public void setStudents(List<StudentDTO> students) { this.students = students; }
    }
    