package com.example.task4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginDto {
    @NotBlank(message = "Email may not be blank")
    @Email(message = "Incorrect email format")
    private String email;
    @NotBlank(message = "Password may not be blank")
    @Size(min = 8, max = 255, message = "Password length should be between 8 and 255 characters")
    private String password;
}
