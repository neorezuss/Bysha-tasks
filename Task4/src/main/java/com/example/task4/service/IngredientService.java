package com.example.task4.service;

import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.IngredientType;

import java.util.List;

public interface IngredientService {
    List<IngredientDto> getUserIngredients(String userEmail, String name, IngredientType type, Integer costGT, Integer costLT, String sortBy);

    boolean buyIngredient(String userEmail, IngredientDto ingredientDto);

    boolean sellIngredient(String userEmail, IngredientDto ingredientDto);
}
