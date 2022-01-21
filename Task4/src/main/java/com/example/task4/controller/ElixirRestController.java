package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.service.ElixirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        List<ElixirDto> elixirDtoList = elixirService.getUserElixirs(filteringParams);
        if (elixirDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(elixirDtoList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> sellElixir(
            @RequestBody ElixirDto elixirDto
    ) {
        if (!elixirService.sellElixir(elixirDto)) {
            return new ResponseEntity<>("You dont have " + elixirDto.getName() + "!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Elixir " + elixirDto.getName() + " was sold!", HttpStatus.OK);
    }
}
