package com.example.task4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
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
