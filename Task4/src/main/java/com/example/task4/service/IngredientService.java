package com.example.task4.service;

import com.example.task4.dto.IngredientDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IngredientService {
    ResponseEntity<List<IngredientDto>> getUserIngredients(Map<String, String> filteringParams);

    ResponseEntity<String> buyIngredient(IngredientDto ingredientDto);

    ResponseEntity<String> sellIngredient(IngredientDto ingredientDto);
}
