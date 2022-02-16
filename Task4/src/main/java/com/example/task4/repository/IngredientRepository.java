package com.example.task4.repository;

import com.example.task4.entity.Ingredient;
import com.example.task4.entity.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>, JpaSpecificationExecutor<Ingredient> {
    Ingredient getIngredientByNameAndTypeAndCost(String name, IngredientType type, Integer cost);
}
