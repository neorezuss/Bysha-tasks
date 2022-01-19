package com.example.task4.dto;

import com.example.task4.entity.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IngredientDto {
    private String name;
    @Enumerated(EnumType.STRING)
    private IngredientType type;
    private int cost;
}
