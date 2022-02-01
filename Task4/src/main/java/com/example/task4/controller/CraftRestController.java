package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import com.example.task4.service.CraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/craft")
@RequiredArgsConstructor
public class CraftRestController {
    private final CraftService craftService;

    @PostMapping("/ingredients")
    public ResponseEntity<String> craftByIngredients(
            @RequestBody List<IngredientDto> ingredientDtoList
    ) {
        return craftService.craftByIngredients(ingredientDtoList);
    }

    @PostMapping("/recipe")
    public ResponseEntity<String> craftByRecipe(
            @RequestBody ElixirDto elixirDto
    ) {
        return craftService.craftByRecipe(elixirDto);
    }
}
