package com.example.task4.repository.specification;

import com.example.task4.entity.Elixir;
import org.springframework.data.jpa.domain.Specification;

public class ElixirSpecification {
    public static Specification<Elixir> filterByUsersEmail(String email) {
        return email == null ? null : (elixir, cq, cb) -> cb.equal(elixir.join("users").get("email"), email);
    }

    public static Specification<Elixir> filterByName(String name) {
        return name == null ? null : (elixir, cq, cb) -> cb.equal(elixir.get("name"), name);
    }

    public static Specification<Elixir> filterByCostGT(Integer costGT) {
        return costGT == null ? null : (elixir, cq, cb) -> cb.greaterThan(elixir.get("cost"), costGT);
    }

    public static Specification<Elixir> filterByCostLT(Integer costLT) {
        return costLT == null ? null : (elixir, cq, cb) -> cb.lessThan(elixir.get("cost"), costLT);
    }

    public static Specification<Elixir> filterByLevel(Integer level) {
        return level == null ? null : (elixir, cq, cb) -> cb.equal(elixir.get("level"), level);
    }
}
