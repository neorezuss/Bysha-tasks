package com.example.task4.dto;

public class ElixirDto {
    private String name;
    private int cost;
    private int level;

    public ElixirDto() {
    }

    public ElixirDto(String name, int cost, int level) {
        this.name = name;
        this.cost = cost;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
