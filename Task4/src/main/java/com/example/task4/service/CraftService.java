package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;

import java.util.List;

public interface CraftService {
    ElixirDto craftByIngredients(List<IngredientDto> ingredientDtoList);

    ElixirDto craftByRecipe(ElixirDto elixirDto);
}
