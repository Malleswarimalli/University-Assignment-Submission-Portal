package com.example.university.model;

// The DTO should use simple types like String for data transfer.
public class StudentDTO {
    private String name;
    private String registerNo;
    private String email;
    private String dob; // This MUST be a String to match the frontend and service logic.

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRegisterNo() { return registerNo; }
    public void setRegisterNo(String registerNo) { this.registerNo = registerNo; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
}
