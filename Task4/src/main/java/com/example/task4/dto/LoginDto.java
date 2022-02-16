package com.example.task4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginDto {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8, max = 255)
    private String password;
}
