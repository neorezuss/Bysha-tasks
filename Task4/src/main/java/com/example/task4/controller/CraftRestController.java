package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import com.example.task4.service.CraftService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/craft")
public class CraftRestController {
    private final CraftService craftService;

    public CraftRestController(CraftService craftService) {
        this.craftService = craftService;
    }

    @PostMapping("/ingredients")
    public String craftByIngredients(
            Authentication authentication,
            @RequestBody List<IngredientDto> ingredientDtoList
    ) {
        if (!craftService.craftByIngredients(authentication.getName(), ingredientDtoList))
            return "Elixir craft is failed!";
        return "Elixir was crafted!";
    }

    @PostMapping("/recipe")
    public String craftByRecipe(
            Authentication authentication,
            @RequestBody ElixirDto elixirDto
    ) {
        if (!craftService.craftByRecipe(authentication.getName(), elixirDto))
            return "Elixir craft is failed!";
        return "Elixir was crafted!";
    }
}
