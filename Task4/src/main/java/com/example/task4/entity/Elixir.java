package com.example.task4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "elixirs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name", "cost", "level"})
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
    private Set<Ingredient> ingredients;

    @ManyToMany(mappedBy = "elixirs")
    @JsonIgnore
    private List<User> users;
}
