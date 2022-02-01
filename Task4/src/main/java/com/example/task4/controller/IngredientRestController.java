package com.example.task4.controller;

import com.example.task4.dto.IngredientDto;
import com.example.task4.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientRestController {
    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getUserIngredients(
            @RequestBody Map<String, String> filteringParams
    ) {
        return ingredientService.getUserIngredients(filteringParams);
    }

    @PostMapping
    public ResponseEntity<String> buyIngredient(
            @RequestBody IngredientDto ingredientDto
    ) {
        return ingredientService.buyIngredient(ingredientDto);
    }

    @DeleteMapping
    public ResponseEntity<String> sellIngredient(
            @RequestBody IngredientDto ingredientDto
    ) {
        return ingredientService.sellIngredient(ingredientDto);
    }
}
