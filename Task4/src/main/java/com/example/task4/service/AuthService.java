package com.example.task4.service;

import com.example.task4.dto.LoginDto;
import com.example.task4.dto.RefreshTokenDto;
import com.example.task4.dto.RegistrationDto;
import com.example.task4.security.AuthResponse;

public interface AuthService {
    AuthResponse authenticateUser(LoginDto loginDto);

    RegistrationDto registerUser(RegistrationDto registrationDto);

    AuthResponse refreshToken(RefreshTokenDto refreshTokenDto);
}
