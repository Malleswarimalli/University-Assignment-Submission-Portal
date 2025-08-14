package com.example.university.service;

import com.example.university.model.LoginRequest;
import com.example.university.model.LoginResponse;
import com.example.university.model.User;
import com.example.university.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public LoginResponse loginUser(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty()) {
            return new LoginResponse("Invalid email or password.");
        }

        User user = userOpt.get();

        // Handle Student Login
        if ("STUDENT".equals(user.getRole())) {
            if (user.getDob() == null) {
                return new LoginResponse("Student account is not fully configured (missing DOB).");
            }
            String dobPassword = user.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (loginRequest.getPassword().equals(dobPassword)) {
                // Return the user's name in the response
                return new LoginResponse(user.getId(), user.getRole(), user.getUsername());
            }
        }
        // Handle Staff/Admin Login
        else if ("INSTRUCTOR".equals(user.getRole()) || "ADMIN".equals(user.getRole())) {
            if (loginRequest.getPassword().equals(user.getPassword())) {
                // Return the user's name in the response
                return new LoginResponse(user.getId(), user.getRole(), user.getUsername());
            }
        }

        return new LoginResponse("Invalid email or password.");
    }
}
