package com.example.task4.service;

import com.example.task4.dto.IngredientDto;
import com.example.task4.dto.IngredientFilteringParamsDto;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.UserInventory;
import com.example.task4.exception.IngredientNotFoundException;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.UserInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.task4.repository.specification.IngredientSpecification.buildSpecification;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private static final String DEFAULT_SORTING = "name";

    private final IngredientRepository ingredientRepository;
    private final UserInventoryRepository userInventoryRepository;

    @Override
    public List<IngredientDto> getUserIngredients(IngredientFilteringParamsDto filteringParams) {
        Specification<Ingredient> specification = buildSpecification(filteringParams);
        Sort.Direction direction = Sort.Direction.fromOptionalString(filteringParams.getSortDirection())
                .orElse(Sort.DEFAULT_DIRECTION);
        String sortBy = nonNull(filteringParams.getSortBy()) ? filteringParams.getSortBy() : DEFAULT_SORTING;
        List<Ingredient> filteredIngredients = ingredientRepository.findAll(specification, Sort.by(direction, sortBy));

        return filteredIngredients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public IngredientDto buyIngredient(IngredientDto ingredientDto) {
        String userEmail = getUserEmail();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);
        Ingredient ingredient = ingredientRepository.getIngredientByNameAndTypeAndCost(
                ingredientDto.getName(), ingredientDto.getType(), ingredientDto.getCost());

        if (isNull(ingredient)) {
            throw new IngredientNotFoundException("There is no ingredient with name" + ingredientDto.getName() + "!");
        }
        if (!(userInventory.getCoins() >= ingredient.getCost())) {
            throw new IllegalStateException("You dont have enough coins!");
        }

        buyIngredient(userInventory, ingredient);
        return ingredientDto;
    }

    @Override
    @Transactional
    public IngredientDto sellIngredient(IngredientDto ingredientDto) {
        String userEmail = getUserEmail();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);
        Ingredient ingredient = ingredientRepository.getIngredientByNameAndTypeAndCost(
                ingredientDto.getName(), ingredientDto.getType(), ingredientDto.getCost());

        if (isNull(ingredient)) {
            throw new IngredientNotFoundException("There is no ingredient with name" + ingredientDto.getName() + "!");
        }
        if (!userInventory.getIngredients().contains(ingredient)) {
            throw new IllegalStateException("You don’t have ingredient with name " + ingredient.getName() + "!");
        }

        sellIngredient(userInventory, ingredient);
        return ingredientDto;
    }

    private String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private IngredientDto convertToDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getName(), ingredient.getType(), ingredient.getCost());
    }

    private void buyIngredient(UserInventory userInventory, Ingredient ingredient) {
        userInventory.setCoins(userInventory.getCoins() - ingredient.getCost());
        userInventory.getIngredients().add(ingredient);
    }

    private void sellIngredient(UserInventory userInventory, Ingredient ingredient) {
        userInventory.getIngredients().remove(ingredient);
        userInventory.setCoins(userInventory.getCoins() + ingredient.getCost());
    }
}
