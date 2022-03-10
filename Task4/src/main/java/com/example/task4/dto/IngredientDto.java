package com.example.task4.dto;

import com.example.task4.entity.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class IngredientDto {
    @NotBlank(message = "Name may not be blank")
    @Size(min = 2, max = 255, message = "Name length should be between 2 and 255 characters")
    private String name;
    @NotBlank(message = "Type may not be blank")
    @Enumerated(EnumType.STRING)
    private IngredientType type;
    @Min(value = 1, message = "Cost should be greater than 0")
    private int cost;
}
