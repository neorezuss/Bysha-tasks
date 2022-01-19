package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.service.ElixirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elixirs")
public class ElixirRestController {
    @Autowired
    private ElixirService elixirService;

    @GetMapping
    public ResponseEntity<List<ElixirDto>> getUserElixirs(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer costGT,
            @RequestParam(required = false) Integer costLT,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String sortDirection
    ) {
        List<ElixirDto> elixirDtoList = elixirService.getUserElixirs(name, costGT, costLT, level, sort, sortDirection);
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
