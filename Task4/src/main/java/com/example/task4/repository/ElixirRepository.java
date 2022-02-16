package com.example.task4.repository;

import com.example.task4.entity.Elixir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ElixirRepository extends JpaRepository<Elixir, Long>, JpaSpecificationExecutor<Elixir> {
    Elixir getElixirByName(String name);
}
