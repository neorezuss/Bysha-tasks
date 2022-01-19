package com.example.task4.service;

import com.example.task4.dto.ElixirDto;

import java.util.List;

public interface ElixirService {
    List<ElixirDto> getUserElixirs(String name, Integer costGT, Integer costLT, Integer level, String sortBy, String sortDirection);

    boolean sellElixir(ElixirDto elixirDto);
}
