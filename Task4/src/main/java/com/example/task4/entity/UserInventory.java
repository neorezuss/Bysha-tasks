package com.example.task4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user_inventories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserInventory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private int coins;

    @ManyToMany
    @JoinTable(
            name = "user_inventory_ingredient",
            joinColumns = @JoinColumn(name = "user_inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @JsonIgnore
    private List<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(
            name = "user_inventory_elixir",
            joinColumns = @JoinColumn(name = "user_inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "elixir_id")
    )
    @JsonIgnore
    private List<Elixir> elixirs;
}
