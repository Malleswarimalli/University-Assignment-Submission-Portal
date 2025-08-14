    package com.example.university.model;

    import java.sql.Timestamp;

    public class StudentAssignmentDTO {
        private Long assignmentId;
        private String title;
        private String description;
        private Timestamp dueDate;
        private String status; // "Not Submitted", "Submitted", "Graded"
        private String grade;

        // Getters and Setters
        public Long getAssignmentId() { return assignmentId; }
        public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Timestamp getDueDate() { return dueDate; }
        public void setDueDate(Timestamp dueDate) { this.dueDate = dueDate; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getGrade() { return grade; }
        public void setGrade(String grade) { this.grade = grade; }
    }
    