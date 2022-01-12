package com.example.task4.service;

import com.example.task4.dto.ElixirDto;

import java.util.List;

public interface ElixirService {
    List<ElixirDto> getUserElixirs(String userEmail, String name, Integer costGT, Integer costLT, Integer level, String sortBy);

    boolean sellElixir(String userEmail, ElixirDto elixirDto);
}
