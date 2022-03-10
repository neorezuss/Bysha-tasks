package com.example.task4.repository.specification;

import com.example.task4.dto.IngredientFilteringParamsDto;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.IngredientType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.isNull;

public class IngredientSpecification {
    public static Specification<Ingredient> filterByUsersEmail(String email) {
        return isNull(email) ? null : (ingredient, cq, cb) -> cb.equal(
                ingredient.join("userInventories").join("user").get("email"),
                email
        );
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

    public static Specification<Ingredient> buildSpecification(IngredientFilteringParamsDto filteringParams) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = filteringParams.getName();
        IngredientType type = filteringParams.getType();
        Integer costGT = filteringParams.getCostGT();
        Integer costLT = filteringParams.getCostLT();

        return Specification
                .where(IngredientSpecification.filterByUsersEmail(userEmail))
                .and(IngredientSpecification.filterByName(name))
                .and(IngredientSpecification.filterByType(type))
                .and(IngredientSpecification.filterByCostGT(costGT))
                .and(IngredientSpecification.filterByCostLT(costLT));
    }
}
