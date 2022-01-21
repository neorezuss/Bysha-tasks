package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;

import java.util.List;

public interface CraftService {
    boolean craftByIngredients(List<IngredientDto> ingredientDtoList);

    boolean craftByRecipe(ElixirDto elixirDto);
}
