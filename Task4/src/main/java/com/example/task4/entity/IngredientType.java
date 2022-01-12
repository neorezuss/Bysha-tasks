package com.example.task4.entity;

public enum IngredientType {
    HERB(75), POWDER(25), LIQUID(100), SOLID(0);

    private int consumeProbability;

    IngredientType(int consumeProbability) {
        this.consumeProbability = consumeProbability;
    }

    public int getConsumeProbability() {
        return consumeProbability;
    }
}
