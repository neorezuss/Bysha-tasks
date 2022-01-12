package com.example.task4.controller;

import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.IngredientType;
import com.example.task4.service.IngredientService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientRestController {
    private final IngredientService ingredientService;

    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<IngredientDto> getUserIngredients(
            Authentication authentication,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) IngredientType type,
            @RequestParam(required = false) Integer costGT,
            @RequestParam(required = false) Integer costLT,
            @RequestParam(required = false) String sort
    ) {
        return ingredientService.getUserIngredients(authentication.getName(), name, type, costGT, costLT, sort);
    }

    @PostMapping
    public String buyIngredient(
            Authentication authentication,
            @RequestBody IngredientDto ingredientDto
    ) {
        if (!ingredientService.buyIngredient(authentication.getName(), ingredientDto))
            return "You dont have enough coins!";
        return "Ingredient " + ingredientDto.getName() + " was bought!";
    }

    @DeleteMapping
    public String sellIngredient(
            Authentication authentication,
            @RequestBody IngredientDto ingredientDto
    ) {
        if (!ingredientService.sellIngredient(authentication.getName(), ingredientDto))
            return "You dont have " + ingredientDto.getName() + "!";
        return "Ingredient " + ingredientDto.getName() + " was sold!";
    }
}
