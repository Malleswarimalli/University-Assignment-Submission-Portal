package com.example.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // This configuration allows the React frontend (running on localhost:5173)
                // to make any type of request (GET, POST, PUT, etc.) to any API endpoint
                // in this backend application.
                registry.addMapping("/api/**") // Apply to all endpoints under /api/
                        .allowedOrigins("http://localhost:5173") // Allow requests from your React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true);
            }
        };
    }
}
