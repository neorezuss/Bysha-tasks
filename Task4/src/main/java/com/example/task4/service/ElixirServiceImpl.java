package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.User;
import com.example.task4.repository.ElixirRepository;
import com.example.task4.repository.specification.ElixirSpecification;
import com.example.task4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElixirServiceImpl implements ElixirService {
    private static final String DEFAULT_SORTING = "name";

    @Autowired
    private ElixirRepository elixirRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ElixirDto> getUserElixirs(String name, Integer costGT, Integer costLT, Integer level, String sortBy, String sortDirection) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
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

        boolean canSell = user.getElixirs().remove(elixir);
        if (canSell) {
            user.setCoins(user.getCoins() + elixir.getCost());
        }
        return canSell;
    }

    private ElixirDto convertToDto(Elixir elixir) {
        return new ElixirDto(elixir.getName(), elixir.getCost(), elixir.getLevel());
    }
}
