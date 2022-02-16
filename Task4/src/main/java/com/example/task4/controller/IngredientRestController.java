package com.example.task4.controller;

import com.example.task4.dto.IngredientDto;
import com.example.task4.dto.IngredientFilteringParamsDto;
import com.example.task4.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientRestController {
    private final IngredientService ingredientService;

    @GetMapping
    public List<IngredientDto> getUserIngredients(
            @RequestBody IngredientFilteringParamsDto filteringParams,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid input data!");
        }
        return ingredientService.getUserIngredients(filteringParams);
    }

    @PostMapping
    public IngredientDto buyIngredient(
            @RequestBody @Valid IngredientDto ingredientDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid input data!");
        }
        return ingredientService.buyIngredient(ingredientDto);
    }

    @DeleteMapping
    public IngredientDto sellIngredient(
            @RequestBody @Valid IngredientDto ingredientDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid input data!");
        }
        return ingredientService.sellIngredient(ingredientDto);
    }
}
