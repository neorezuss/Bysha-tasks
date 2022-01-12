package com.example.task4.dto;

import com.example.task4.entity.IngredientType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class IngredientDto {
    private String name;
    @Enumerated(EnumType.STRING)
    private IngredientType type;
    private int cost;

    public IngredientDto() {
    }

    public IngredientDto(String name, IngredientType type, int cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
