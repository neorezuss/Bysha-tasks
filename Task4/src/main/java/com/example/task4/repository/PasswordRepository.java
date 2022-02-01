package com.example.task4.repository;

import com.example.task4.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    Password findByUserEmail(String email);
}
