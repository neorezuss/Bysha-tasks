package com.example.task4.controller;

import com.example.task4.dto.IngredientDto;
import com.example.task4.dto.IngredientFilteringParamsDto;
import com.example.task4.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientRestController {
    private final IngredientService ingredientService;

    @GetMapping
    public List<IngredientDto> getUserIngredients(
            @Valid @RequestBody IngredientFilteringParamsDto filteringParams
    ) {
        return ingredientService.getUserIngredients(filteringParams);
    }

    @PostMapping
    public IngredientDto buyIngredient(
            @Valid @RequestBody IngredientDto ingredientDto
    ) {
        return ingredientService.buyIngredient(ingredientDto);
    }

    @DeleteMapping
    public IngredientDto sellIngredient(
            @Valid @RequestBody IngredientDto ingredientDto
    ) {
        return ingredientService.sellIngredient(ingredientDto);
    }
}
