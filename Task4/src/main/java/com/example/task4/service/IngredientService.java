package com.example.task4.service;

import com.example.task4.dto.IngredientDto;
import com.example.task4.dto.IngredientFilteringParamsDto;

import java.util.List;

public interface IngredientService {
    List<IngredientDto> getUserIngredients(IngredientFilteringParamsDto filteringParams);

    IngredientDto buyIngredient(IngredientDto ingredientDto);

    IngredientDto sellIngredient(IngredientDto ingredientDto);
}
