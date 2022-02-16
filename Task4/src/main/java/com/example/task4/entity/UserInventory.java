package com.example.task4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
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
    private long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @JoinColumn(name = "coins")
    private int coins;
    @ManyToMany
    @JoinTable(
            name = "user_inventory_ingredient",
            joinColumns = @JoinColumn(name = "user_inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;
    @ManyToMany
    @JoinTable(
            name = "user_inventory_elixir",
            joinColumns = @JoinColumn(name = "user_inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "elixir_id")
    )
    private List<Elixir> elixirs;
}
