package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.ElixirFilteringParamsDto;

import java.util.List;

public interface ElixirService {
    List<ElixirDto> getUserElixirs(ElixirFilteringParamsDto filteringParams);

    ElixirDto sellElixir(ElixirDto elixirDto);
}
