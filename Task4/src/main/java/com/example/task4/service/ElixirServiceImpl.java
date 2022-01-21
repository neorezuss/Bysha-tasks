package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.User;
import com.example.task4.repository.ElixirRepository;
import com.example.task4.repository.UserRepository;
import com.example.task4.repository.specification.ElixirSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ElixirServiceImpl implements ElixirService {
    private static final String DEFAULT_SORTING = "name";

    private final ElixirRepository elixirRepository;
    private final UserRepository userRepository;

    @Override
    public List<ElixirDto> getUserElixirs(Map<String, String> filteringParams) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = filteringParams.get("name");
        Integer costGT = nonNull(filteringParams.get("costGT")) ? Integer.valueOf(filteringParams.get("costGT")) : null;
        Integer costLT = nonNull(filteringParams.get("costLT")) ? Integer.valueOf(filteringParams.get("costLT")) : null;
        Integer level = nonNull(filteringParams.get("level")) ? Integer.valueOf(filteringParams.get("level")) : null;
        String sortBy = filteringParams.get("sortBy");
        String sortDirection = filteringParams.get("sortDirection");

        Sort.Direction direction = Sort.Direction.fromOptionalString(sortDirection).orElse(Sort.DEFAULT_DIRECTION);
        Specification<Elixir> specification = Specification
                .where(ElixirSpecification.filterByUsersEmail(userEmail))
                .and(ElixirSpecification.filterByName(name))
                .and(ElixirSpecification.filterByCostGT(costGT))
                .and(ElixirSpecification.filterByCostLT(costLT))
                .and(ElixirSpecification.filterByLevel(level));

        List<Elixir> filteredElixirs;
        try {
            filteredElixirs = elixirRepository.findAll(specification, Sort.by(direction, sortBy));
        } catch (PropertyReferenceException | IllegalArgumentException e) {
            filteredElixirs = elixirRepository.findAll(specification, Sort.by(DEFAULT_SORTING));
        }
        return filteredElixirs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean sellElixir(ElixirDto elixirDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(userEmail);
        Elixir elixir = elixirRepository.getElixirByName(elixirDto.getName());

        boolean canSell = nonNull(elixir) && user.getElixirs().contains(elixir);
        if (canSell) {
            user.getElixirs().remove(elixir);
            user.setCoins(user.getCoins() + elixir.getCost());
        }
        return canSell;
    }

    private ElixirDto convertToDto(Elixir elixir) {
        return new ElixirDto(elixir.getName(), elixir.getCost(), elixir.getLevel());
    }
}