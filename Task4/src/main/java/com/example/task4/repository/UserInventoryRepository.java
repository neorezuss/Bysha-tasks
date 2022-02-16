package com.example.task4.repository;

import com.example.task4.entity.UserInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInventoryRepository extends JpaRepository<UserInventory, Long> {
    UserInventory getByUserEmail(String email);
}
