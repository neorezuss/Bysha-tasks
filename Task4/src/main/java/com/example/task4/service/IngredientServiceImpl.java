package com.example.task4.service;

import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.IngredientType;
import com.example.task4.entity.UserInventory;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.UserInventoryRepository;
import com.example.task4.repository.specification.IngredientSpecification;
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
public class IngredientServiceImpl implements IngredientService {
    private static final String DEFAULT_SORTING = "name";

    private final IngredientRepository ingredientRepository;
    private final UserInventoryRepository userInventoryRepository;

    @Override
    public ResponseEntity<List<IngredientDto>> getUserIngredients(Map<String, String> filteringParams) {
        Specification<Ingredient> specification = buildSpecification(filteringParams);
        Sort.Direction direction = Sort.Direction.fromOptionalString(filteringParams.get("sortDirection")).orElse(Sort.DEFAULT_DIRECTION);

        List<Ingredient> filteredIngredients = ingredientRepository.findAll(specification, Sort.by(direction, filteringParams.get("sortBy")));

        if (filteredIngredients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<IngredientDto> ingredientDtoList = filteredIngredients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ingredientDtoList, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> buyIngredient(IngredientDto ingredientDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);
        Ingredient ingredient = ingredientRepository.getIngredientByName(ingredientDto.getName());

        if (isNull(ingredient)) {
            return new ResponseEntity<>("There is no ingredient with name" + ingredientDto.getName() + "!", HttpStatus.NOT_FOUND);
        }
        if (!(userInventory.getCoins() >= ingredient.getCost())) {
            return new ResponseEntity<>("You dont have enough coins!", HttpStatus.CONFLICT);
        }

        buyIngredient(userInventory, ingredient);
        return new ResponseEntity<>("Ingredient with name " + ingredient.getName() + " was bought!", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> sellIngredient(IngredientDto ingredientDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);
        Ingredient ingredient = ingredientRepository.getIngredientByName(ingredientDto.getName());

        if (isNull(ingredient)) {
            return new ResponseEntity<>("There is no ingredient with name" + ingredientDto.getName() + "!", HttpStatus.NOT_FOUND);
        }
        if (!userInventory.getIngredients().contains(ingredient)) {
            return new ResponseEntity<>("You don’t have ingredient with name " + ingredient.getName() + "!", HttpStatus.CONFLICT);
        }

        sellIngredient(userInventory, ingredient);
        return new ResponseEntity<>("Ingredient " + ingredientDto.getName() + " was sold!", HttpStatus.OK);
    }

    private Specification<Ingredient> buildSpecification(Map<String, String> filteringParams) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = filteringParams.get("name");
        IngredientType type = nonNull(filteringParams.get("type")) ? IngredientType.valueOf(filteringParams.get("type")) : null;
        Integer costGT = nonNull(filteringParams.get("costGT")) ? Integer.valueOf(filteringParams.get("costGT")) : null;
        Integer costLT = nonNull(filteringParams.get("costLT")) ? Integer.valueOf(filteringParams.get("costLT")) : null;

        return Specification
                .where(IngredientSpecification.filterByUsersEmail(userEmail))
                .and(IngredientSpecification.filterByName(name))
                .and(IngredientSpecification.filterByType(type))
                .and(IngredientSpecification.filterByCostGT(costGT))
                .and(IngredientSpecification.filterByCostLT(costLT));
    }

    private void buyIngredient(UserInventory userInventory, Ingredient ingredient) {
        userInventory.setCoins(userInventory.getCoins() - ingredient.getCost());
        userInventory.getIngredients().add(ingredient);
    }

    private void sellIngredient(UserInventory userInventory, Ingredient ingredient) {
        userInventory.getIngredients().remove(ingredient);
        userInventory.setCoins(userInventory.getCoins() + ingredient.getCost());
    }

    private IngredientDto convertToDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getName(), ingredient.getType(), ingredient.getCost());
    }
}
