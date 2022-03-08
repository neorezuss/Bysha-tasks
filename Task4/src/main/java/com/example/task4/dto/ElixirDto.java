package com.example.task4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ElixirDto {
    @NotBlank(message = "Name may not be blank")
    @Size(min = 2, max = 255, message = "Name length should be between 2 and 255 characters")
    private String name;
    @Min(value = 1, message = "Cost should be greater than 0")
    private int cost;
    @Min(value = 1, message = "Level should be greater than 0")
    private int level;
}
