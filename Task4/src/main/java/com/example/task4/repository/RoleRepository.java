package com.example.task4.repository;

import com.example.task4.entity.Role;
import com.example.task4.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(RoleEnum name);
}
