package com.example.task4.service;

import com.example.task4.dto.ElixirDto;

import java.util.List;
import java.util.Map;

public interface ElixirService {
    List<ElixirDto> getUserElixirs(Map<String, String> filteringParams);

    boolean sellElixir(ElixirDto elixirDto);
}
