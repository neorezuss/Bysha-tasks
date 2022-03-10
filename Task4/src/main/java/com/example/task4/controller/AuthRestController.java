package com.example.task4.controller;

import com.example.task4.dto.LoginDto;
import com.example.task4.dto.RefreshTokenDto;
import com.example.task4.dto.RegistrationDto;
import com.example.task4.security.AuthResponse;
import com.example.task4.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse authenticateUser(
            @Valid @RequestBody LoginDto loginDto
    ) {
        return authService.authenticateUser(loginDto);
    }

    @PostMapping("/register")
    public RegistrationDto registerUser(
            @Valid @RequestBody RegistrationDto registrationDto
    ) {
        return authService.registerUser(registrationDto);
    }

    @PostMapping("/refreshtoken")
    public AuthResponse refreshToken(
            @Valid @RequestBody RefreshTokenDto refreshTokenDto
    ) {
        return authService.refreshTokens(refreshTokenDto);
    }
}
