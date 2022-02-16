package com.example.task4.dto;

import com.example.task4.entity.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class IngredientDto {
    @NotEmpty
    @Size(min = 2, max = 255)
    private String name;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private IngredientType type;
    @NotEmpty
    @Size(min = 1)
    private int cost;
}
