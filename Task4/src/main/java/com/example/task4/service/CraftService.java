package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CraftService {
    ResponseEntity<String> craftByIngredients(List<IngredientDto> ingredientDtoList);

    ResponseEntity<String> craftByRecipe(ElixirDto elixirDto);
}
