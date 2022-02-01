package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ElixirService {
    ResponseEntity<List<ElixirDto>> getUserElixirs(Map<String, String> filteringParams);

    ResponseEntity<String> sellElixir(ElixirDto elixirDto);
}
