package com.example.task4.service;

import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.IngredientType;

import java.util.List;

public interface IngredientService {
    List<IngredientDto> getUserIngredients(String name, IngredientType type, Integer costGT, Integer costLT, String sortBy, String sortDirection);

    boolean buyIngredient(IngredientDto ingredientDto);

    boolean sellIngredient(IngredientDto ingredientDto);
}
