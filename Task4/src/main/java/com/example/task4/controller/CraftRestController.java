package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import com.example.task4.service.CraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/craft")
@RequiredArgsConstructor
public class CraftRestController {
    private final CraftService craftService;

    @PostMapping("/ingredients")
    public ElixirDto craftByIngredients(
            @RequestBody @Valid List<IngredientDto> ingredientDtoList,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid input data!");
        }
        return craftService.craftByIngredients(ingredientDtoList);
    }

    @PostMapping("/recipe")
    public ElixirDto craftByRecipe(
            @RequestBody @Valid ElixirDto elixirDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid input data!");
        }
        return craftService.craftByRecipe(elixirDto);
    }
}
