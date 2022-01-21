package com.example.task4.controller;

import com.example.task4.dto.IngredientDto;
import com.example.task4.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        List<IngredientDto> ingredientDtoList = ingredientService.getUserIngredients(filteringParams);
        if (ingredientDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ingredientDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> buyIngredient(
            @RequestBody IngredientDto ingredientDto
    ) {
        if (!ingredientService.buyIngredient(ingredientDto)) {
            return new ResponseEntity<>("You dont have enough coins!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Ingredient " + ingredientDto.getName() + " was bought!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> sellIngredient(
            @RequestBody IngredientDto ingredientDto
    ) {
        if (!ingredientService.sellIngredient(ingredientDto)) {
            return new ResponseEntity<>("You dont have " + ingredientDto.getName() + "!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Ingredient " + ingredientDto.getName() + " was sold!", HttpStatus.OK);
    }
}
