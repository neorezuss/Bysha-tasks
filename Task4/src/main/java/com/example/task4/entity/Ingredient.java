package com.example.task4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name", "type", "cost"})
public class Ingredient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private IngredientType type;
    private int cost;

    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private Set<Elixir> elixirs;

    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private List<User> users;
}
