package com.example.task4.repository.specification;

import com.example.task4.entity.Ingredient;
import com.example.task4.entity.IngredientType;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Objects.isNull;

public class IngredientSpecification {
    public static Specification<Ingredient> filterByUsersEmail(String email) {
        return isNull(email) ? null : (ingredient, cq, cb) -> cb.equal(ingredient.join("users").get("email"), email);
    }

    public static Specification<Ingredient> filterByName(String name) {
        return isNull(name) ? null : (ingredient, cq, cb) -> cb.equal(ingredient.get("name"), name);
    }

    public static Specification<Ingredient> filterByType(IngredientType type) {
        return isNull(type) ? null : (ingredient, cq, cb) -> cb.equal(ingredient.get("type"), type);
    }

    public static Specification<Ingredient> filterByCostGT(Integer costGT) {
        return isNull(costGT) ? null : (ingredient, cq, cb) -> cb.greaterThan(ingredient.get("cost"), costGT);
    }

    public static Specification<Ingredient> filterByCostLT(Integer costLT) {
        return isNull(costLT) ? null : (ingredient, cq, cb) -> cb.lessThan(ingredient.get("cost"), costLT);
    }
}
