package com.example.task4.repository.specification;

import com.example.task4.entity.Elixir;
import com.example.task4.entity.Ingredient;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Objects.isNull;

public class ElixirSpecification {
    public static Specification<Elixir> filterByUsersEmail(String email) {
        return isNull(email) ? null : (elixir, cq, cb) -> cb.equal(elixir.join("userInventories").join("user").get("email"), email);
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
}
