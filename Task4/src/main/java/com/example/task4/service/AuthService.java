package com.example.task4.service;

import com.example.task4.dto.RegistrationDto;
import com.example.task4.entity.User;

public interface AuthService {
    void saveUser(RegistrationDto registrationDto);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    User findByEmailAndPassword(String email, String password);
}
