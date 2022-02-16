package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.ElixirFilteringParamsDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.UserInventory;
import com.example.task4.exception.ElixirNotFoundException;
import com.example.task4.repository.ElixirRepository;
import com.example.task4.repository.UserInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.task4.repository.specification.ElixirSpecification.buildSpecification;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ElixirServiceImpl implements ElixirService {
    private static final String DEFAULT_SORTING = "name";

    private final ElixirRepository elixirRepository;
    private final UserInventoryRepository userInventoryRepository;

    @Override
    public List<ElixirDto> getUserElixirs(ElixirFilteringParamsDto filteringParams) {
        Specification<Elixir> specification = buildSpecification(filteringParams);
        Sort.Direction direction = Sort.Direction.fromOptionalString(filteringParams.getSortDirection())
                .orElse(Sort.DEFAULT_DIRECTION);
        String sortBy = nonNull(filteringParams.getSortBy()) ? filteringParams.getSortBy() : DEFAULT_SORTING;
        List<Elixir> filteredElixirs = elixirRepository.findAll(specification, Sort.by(direction, sortBy));

        List<ElixirDto> elixirDtoList = filteredElixirs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return elixirDtoList;
    }

    @Override
    @Transactional
    public ElixirDto sellElixir(ElixirDto elixirDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);
        Elixir elixir = elixirRepository.getElixirByName(elixirDto.getName());

        if (isNull(elixir)) {
            throw new ElixirNotFoundException("There is no elixir with name " + elixirDto.getName() + "!");
        }
        if (!userInventory.getElixirs().contains(elixir)) {
            throw new IllegalStateException("You don’t have elixir with name " + elixir.getName() + "!");
        }

        sellElixir(userInventory, elixir);
        return elixirDto;
    }

    private ElixirDto convertToDto(Elixir elixir) {
        return new ElixirDto(elixir.getName(), elixir.getCost(), elixir.getLevel());
    }

    private void sellElixir(UserInventory userInventory, Elixir elixir) {
        userInventory.getElixirs().remove(elixir);
        userInventory.setCoins(userInventory.getCoins() + elixir.getCost());
    }
}