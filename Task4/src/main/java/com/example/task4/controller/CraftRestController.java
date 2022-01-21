package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import com.example.task4.service.CraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        if (!craftService.craftByIngredients(ingredientDtoList)) {
            return new ResponseEntity<>("Elixir craft is failed!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Elixir was crafted!", HttpStatus.CREATED);
    }

    @PostMapping("/recipe")
    public ResponseEntity<String> craftByRecipe(
            @RequestBody ElixirDto elixirDto
    ) {
        if (!craftService.craftByRecipe(elixirDto)) {
            return new ResponseEntity<>("Elixir craft is failed!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Elixir was crafted!", HttpStatus.CREATED);
    }
}
