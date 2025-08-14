package com.example.university.model;

public class SubmissionStatusDTO {
    private Long studentId;
    private String studentName;
    private String registerNo;
    private Long submissionId;
    private String grade;
    private String status;
    private String answerText; // NEW FIELD for the student's answer

    // Getters and Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getRegisterNo() { return registerNo; }
    public void setRegisterNo(String registerNo) { this.registerNo = registerNo; }
    public Long getSubmissionId() { return submissionId; }
    public void setSubmissionId(Long submissionId) { this.submissionId = submissionId; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getAnswerText() { return answerText; } // Getter for the new field
    public void setAnswerText(String answerText) { this.answerText = answerText; } // Setter for the new field
}
