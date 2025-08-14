    package com.example.university.model;

    import java.time.LocalDate;

    public class StudentProfileDTO {
        private String studentName;
        private String email;
        private String registerNo;
        private LocalDate dob;
        private String courseName;
        private Long courseId;

        // Getters and Setters
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getRegisterNo() { return registerNo; }
        public void setRegisterNo(String registerNo) { this.registerNo = registerNo; }
        public LocalDate getDob() { return dob; }
        public void setDob(LocalDate dob) { this.dob = dob; }
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        public Long getCourseId() { return courseId; }
        public void setCourseId(Long courseId) { this.courseId = courseId; }
    }
    