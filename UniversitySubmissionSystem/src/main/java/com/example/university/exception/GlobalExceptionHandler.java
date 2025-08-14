package com.example.university.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method catches the specific IllegalStateException we throw from our service
     * when a duplicate email is found.
     * @param ex The exception that was caught.
     * @return A ResponseEntity with a 400 Bad Request status and a clean JSON error message.
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage()); // Get the specific message from the exception
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * NEW "CATCH-ALL" HANDLER
     * This method catches any other unexpected exceptions that occur on the server.
     * This prevents the generic 500 Internal Server Error.
     * @param ex The generic exception that was caught.
     * @param request The web request during which the exception occurred.
     * @return A ResponseEntity with a 500 Internal Server Error status and a generic message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, String> errorResponse = new HashMap<>();
        // In a real production app, you would log the full exception: ex.printStackTrace();
        System.err.println("An unexpected error occurred: " + ex.getMessage());
        
        errorResponse.put("message", "An unexpected error occurred on the server. Please check the backend logs for details.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
