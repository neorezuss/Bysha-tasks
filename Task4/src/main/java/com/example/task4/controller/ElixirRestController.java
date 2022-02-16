package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.ElixirFilteringParamsDto;
import com.example.task4.service.ElixirService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/elixirs")
@RequiredArgsConstructor
public class ElixirRestController {
    private final ElixirService elixirService;

    @GetMapping
    public List<ElixirDto> getUserElixirs(
            @RequestBody @Valid ElixirFilteringParamsDto filteringParams,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid input data!");
        }
        return elixirService.getUserElixirs(filteringParams);
    }

    @DeleteMapping
    public ElixirDto sellElixir(
            @RequestBody @Valid ElixirDto elixirDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid input data!");
        }
        return elixirService.sellElixir(elixirDto);
    }
}
