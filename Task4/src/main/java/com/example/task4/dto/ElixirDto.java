package com.example.task4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ElixirDto {
    @NotEmpty
    @Size(min = 2, max = 255)
    private String name;
    @NotEmpty
    @Size(min = 1)
    private int cost;
    @NotEmpty
    @Size(min = 1)
    private int level;
}
