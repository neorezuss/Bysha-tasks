package com.example.task4.security;

import lombok.Value;

@Value
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
