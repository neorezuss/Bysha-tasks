package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.service.ElixirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/elixirs")
@RequiredArgsConstructor
public class ElixirRestController {
    private final ElixirService elixirService;

    @GetMapping
    public ResponseEntity<List<ElixirDto>> getUserElixirs(
            @RequestBody Map<String, String> filteringParams
    ) {
        return elixirService.getUserElixirs(filteringParams);
    }

    @DeleteMapping
    public ResponseEntity<String> sellElixir(
            @RequestBody ElixirDto elixirDto
    ) {
        return elixirService.sellElixir(elixirDto);
    }
}
