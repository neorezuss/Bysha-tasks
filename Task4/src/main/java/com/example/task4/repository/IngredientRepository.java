package com.example.task4.repository;

import com.example.task4.entity.Elixir;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.IngredientType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT i FROM Ingredient i join i.users u where u.email = :email " +
            "and (:name is null or i.name = :name) and (:type is null or i.type = :type) " +
            "and (:costGT is null or i.cost > :costGT) and (:costLT is null or i.cost < :costLT)")
    List<Ingredient> findIngredientsByUsersEmailAndByNameAndByTypeAndByCost(
            @Param("email") String email, @Param("name") String name, @Param("type") IngredientType type,
            @Param("costGT") Integer costGT, @Param("costLT") Integer costLT, Sort sort);

    Ingredient getIngredientByName(String name);
}
