package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.UserInventory;
import com.example.task4.repository.ElixirRepository;
import com.example.task4.repository.UserInventoryRepository;
import com.example.task4.repository.specification.ElixirSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ElixirServiceImpl implements ElixirService {
    private static final String DEFAULT_SORTING = "name";

    private final ElixirRepository elixirRepository;
    private final UserInventoryRepository userInventoryRepository;

    @Override
    public ResponseEntity<List<ElixirDto>> getUserElixirs(Map<String, String> filteringParams) {
        Specification<Elixir> specification = buildSpecification(filteringParams);
        Sort.Direction direction = Sort.Direction.fromOptionalString(filteringParams.get("sortDirection"))
                .orElse(Sort.DEFAULT_DIRECTION);

        List<Elixir> filteredElixirs = elixirRepository.findAll(specification, Sort.by(direction, filteringParams.get("sortBy")));

        if (filteredElixirs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ElixirDto> elixirDtoList = filteredElixirs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(elixirDtoList, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> sellElixir(ElixirDto elixirDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);
        Elixir elixir = elixirRepository.getElixirByName(elixirDto.getName());

        if (isNull(elixir)) {
            return new ResponseEntity<>("There is no elixir with name " + elixirDto.getName() + "!", HttpStatus.NOT_FOUND);
        }
        if (!userInventory.getElixirs().contains(elixir)) {
            return new ResponseEntity<>("You don’t have elixir with name " + elixir.getName() + "!", HttpStatus.NOT_FOUND);
        }

        sellElixir(userInventory, elixir);
        return new ResponseEntity<>("Elixir with name " + elixirDto.getName() + " was sold!", HttpStatus.OK);
    }

    private Specification<Elixir> buildSpecification(Map<String, String> filteringParams) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = filteringParams.get("name");
        Integer costGT = nonNull(filteringParams.get("costGT")) ? Integer.valueOf(filteringParams.get("costGT")) : null;
        Integer costLT = nonNull(filteringParams.get("costLT")) ? Integer.valueOf(filteringParams.get("costLT")) : null;
        Integer level = nonNull(filteringParams.get("level")) ? Integer.valueOf(filteringParams.get("level")) : null;

        return Specification
                .where(ElixirSpecification.filterByUsersEmail(userEmail))
                .and(ElixirSpecification.filterByName(name))
                .and(ElixirSpecification.filterByCostGT(costGT))
                .and(ElixirSpecification.filterByCostLT(costLT))
                .and(ElixirSpecification.filterByLevel(level));
    }

    private void sellElixir(UserInventory userInventory, Elixir elixir) {
        userInventory.getElixirs().remove(elixir);
        userInventory.setCoins(userInventory.getCoins() + elixir.getCost());
    }

    private ElixirDto convertToDto(Elixir elixir) {
        return new ElixirDto(elixir.getName(), elixir.getCost(), elixir.getLevel());
    }
}