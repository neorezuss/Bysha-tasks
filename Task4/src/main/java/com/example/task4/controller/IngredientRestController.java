package com.example.task4.controller;

import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.IngredientType;
import com.example.task4.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientRestController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getUserIngredients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) IngredientType type,
            @RequestParam(required = false) Integer costGT,
            @RequestParam(required = false) Integer costLT,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String sortDirection
    ) {
        List<IngredientDto> ingredientDtoList = ingredientService.getUserIngredients(name, type, costGT, costLT, sort, sortDirection);
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
