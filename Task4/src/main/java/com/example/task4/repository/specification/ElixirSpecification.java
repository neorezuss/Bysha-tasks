package com.example.task4.repository.specification;

import com.example.task4.dto.ElixirFilteringParamsDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.Ingredient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.isNull;

public class ElixirSpecification {
    public static Specification<Elixir> filterByUsersEmail(String email) {
        return isNull(email) ? null : (elixir, cq, cb) -> cb.equal(
                elixir.join("userInventories").join("user").get("email"),
                email
        );
    }

    public static Specification<Elixir> filterByName(String name) {
        return isNull(name) ? null : (elixir, cq, cb) -> cb.equal(elixir.get("name"), name);
    }

    public static Specification<Elixir> filterByCostGT(Integer costGT) {
        return isNull(costGT) ? null : (elixir, cq, cb) -> cb.greaterThan(elixir.get("cost"), costGT);
    }

    public static Specification<Elixir> filterByCostLT(Integer costLT) {
        return isNull(costLT) ? null : (elixir, cq, cb) -> cb.lessThan(elixir.get("cost"), costLT);
    }

    public static Specification<Elixir> filterByLevel(Integer level) {
        return isNull(level) ? null : (elixir, cq, cb) -> cb.equal(elixir.get("level"), level);
    }

    public static Specification<Elixir> filterByIngredient(Ingredient ingredient) {
        return isNull(ingredient) ? null : (elixir, cq, cb) -> cb.isMember(ingredient, elixir.get("ingredients"));
    }

    public static Specification<Elixir> buildSpecification(ElixirFilteringParamsDto filteringParams) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = filteringParams.getName();
        Integer costGT = filteringParams.getCostGT();
        Integer costLT = filteringParams.getCostLT();
        Integer level = filteringParams.getLevel();

        return Specification
                .where(ElixirSpecification.filterByUsersEmail(userEmail))
                .and(ElixirSpecification.filterByName(name))
                .and(ElixirSpecification.filterByCostGT(costGT))
                .and(ElixirSpecification.filterByCostLT(costLT))
                .and(ElixirSpecification.filterByLevel(level));
    }
}
