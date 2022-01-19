package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import com.example.task4.service.CraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/craft")
public class CraftRestController {
    @Autowired
    private CraftService craftService;

    @PostMapping("/ingredients")
    public ResponseEntity<String> craftByIngredients(
            Authentication authentication,
            @RequestBody List<IngredientDto> ingredientDtoList
    ) {
        if (!craftService.craftByIngredients(authentication.getName(), ingredientDtoList)) {
            return new ResponseEntity<>("Elixir craft is failed!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Elixir was crafted!", HttpStatus.CREATED);
    }

    @PostMapping("/recipe")
    public ResponseEntity<String> craftByRecipe(
            Authentication authentication,
            @RequestBody ElixirDto elixirDto
    ) {
        if (!craftService.craftByRecipe(authentication.getName(), elixirDto)) {
            return new ResponseEntity<>("Elixir craft is failed!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Elixir was crafted!", HttpStatus.CREATED);
    }
}
