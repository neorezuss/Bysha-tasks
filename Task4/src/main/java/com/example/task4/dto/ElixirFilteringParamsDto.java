package com.example.task4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ElixirFilteringParamsDto {
    @Size(min = 2, max = 255)
    private String name;
    @Size(min = 1)
    private Integer costGT;
    @Size(min = 1)
    private Integer costLT;
    @Size(min = 1)
    private Integer level;
    @Size(min = 2, max = 255)
    private String sortBy;
    @Size(min = 2, max = 255)
    private String sortDirection;
}
