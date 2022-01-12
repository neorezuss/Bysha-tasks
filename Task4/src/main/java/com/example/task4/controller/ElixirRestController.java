package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.service.ElixirService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elixirs")
public class ElixirRestController {
    private final ElixirService elixirService;

    public ElixirRestController(ElixirService elixirService) {
        this.elixirService = elixirService;
    }

    @GetMapping
    public List<ElixirDto> getUserElixirs(
            Authentication authentication,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer costGT,
            @RequestParam(required = false) Integer costLT,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String sort
    ) {
        return elixirService.getUserElixirs(authentication.getName(), name, costGT, costLT, level, sort);
    }

    @DeleteMapping
    public String sellElixir(
            Authentication authentication,
            @RequestBody ElixirDto elixirDto
    ) {
        if (!elixirService.sellElixir(authentication.getName(), elixirDto))
            return "You dont have " + elixirDto.getName() + "!";
        return "Elixir " + elixirDto.getName() + " was sold!";
    }
}
