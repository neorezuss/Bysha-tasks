package com.example.task4.service;

import com.example.task4.dto.LoginDto;
import com.example.task4.dto.RegistrationDto;
import com.example.task4.security.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthResponse> authenticateUser(LoginDto loginDto);

    ResponseEntity<String> registerUser(RegistrationDto registrationDto);
}
