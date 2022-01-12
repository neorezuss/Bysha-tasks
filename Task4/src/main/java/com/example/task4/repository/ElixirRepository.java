package com.example.task4.repository;

import com.example.task4.entity.Elixir;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElixirRepository extends JpaRepository<Elixir, Long> {
    @Query("SELECT e FROM Elixir e join e.users u where u.email = :email " +
            "and (:name is null or e.name = :name) and (:costGT is null or e.cost > :costGT) " +
            "and (:costLT is null or e.cost < :costLT) and (:level is null or e.level = :level) ")
    List<Elixir> findElixirsByUsersEmailAndByNameAndByCostAndByLevel(
            @Param("email") String email, @Param("name") String name, @Param("costGT") Integer costGT,
            @Param("costLT") Integer costLT, @Param("level") Integer level, Sort sort);

    Elixir getElixirByName(String name);
}
