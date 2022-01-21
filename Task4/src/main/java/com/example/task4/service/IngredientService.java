package com.example.task4.service;

import com.example.task4.dto.IngredientDto;

import java.util.List;
import java.util.Map;

public interface IngredientService {
    List<IngredientDto> getUserIngredients(Map<String, String> filteringParams);

    boolean buyIngredient(IngredientDto ingredientDto);

    boolean sellIngredient(IngredientDto ingredientDto);
}
