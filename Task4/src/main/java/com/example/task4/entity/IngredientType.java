package com.example.task4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IngredientType {
    HERB(75), POWDER(25), LIQUID(100), SOLID(0);

    private int consumeProbability;
}
