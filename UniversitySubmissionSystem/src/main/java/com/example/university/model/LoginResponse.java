package com.example.university.model;

public class LoginResponse {
    private Long userId;
    private String role;
    private String username; // NEW FIELD to hold the user's name
    private String message;

    // Constructor for failed login
    public LoginResponse(String message) {
        this.message = message;
    }

    // Updated constructor for successful login
    public LoginResponse(Long userId, String role, String username) {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.message = "Login Successful";
    }

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getUsername() { return username; } // Getter for new field
    public void setUsername(String username) { this.username = username; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
