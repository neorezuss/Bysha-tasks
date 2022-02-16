package com.example.task4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RefreshTokenDto {
    @NotEmpty
    @Size(max = 255)
    private String refreshToken;
}
