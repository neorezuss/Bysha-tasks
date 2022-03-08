package com.example.task4.dto;

import com.example.task4.entity.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class IngredientFilteringParamsDto {
    @Size(min = 2, max = 255, message = "Name length should be between 2 and 255 characters")
    private String name;
    @Size(min = 2, max = 255, message = "Type length should be between 2 and 255 characters")
    private IngredientType type;
    @Min(value = 1, message = "CostGT should be greater than 0")
    private Integer costGT;
    @Min(value = 1, message = "CostLT should be greater than 0")
    private Integer costLT;
    @Size(min = 2, max = 255, message = "Sort length should be between 2 and 255 characters")
    private String sortBy;
    @Size(min = 2, max = 255, message = "Sort direction length should be between 2 and 255 characters")
    private String sortDirection;
}
