package com.example.task4.controller;

import com.example.task4.dto.LoginDto;
import com.example.task4.dto.RefreshTokenDto;
import com.example.task4.dto.RegistrationDto;
import com.example.task4.security.AuthResponse;
import com.example.task4.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse authenticateUser(
            @RequestBody @Valid LoginDto loginDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid authentication data!");
        }
        return authService.authenticateUser(loginDto);
    }

    @PostMapping("/register")
    public RegistrationDto registerUser(
            @RequestBody @Valid RegistrationDto registrationDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid registration data!");
        }
        return authService.registerUser(registrationDto);
    }

    @PostMapping("/refreshtoken")
    public AuthResponse refreshToken(
            @RequestBody @Valid RefreshTokenDto refreshTokenDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid registration data!");
        }
        return authService.refreshTokens(refreshTokenDto);
    }
}
