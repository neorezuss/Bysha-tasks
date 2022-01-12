package com.example.task4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "elixirs")
public class Elixir {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int cost;
    private int level;

    @ManyToMany

    @JoinTable(
            name = "elixir_ingredient",
            joinColumns = @JoinColumn(name = "elixir_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @JsonIgnore
    private List<Ingredient> ingredients;

    @ManyToMany(mappedBy = "elixirs")
    @JsonIgnore
    private List<User> users;

    public Elixir() {
    }

    public Elixir(long id, String name, int cost, int level) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elixir elixir = (Elixir) o;
        return id == elixir.id && cost == elixir.cost && level == elixir.level && Objects.equals(name, elixir.name) && Objects.equals(ingredients, elixir.ingredients) && Objects.equals(users, elixir.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, level);
    }
}
