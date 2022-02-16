package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.UserInventory;
import com.example.task4.exception.ElixirNotFoundException;
import com.example.task4.repository.ElixirRepository;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.UserInventoryRepository;
import com.example.task4.repository.specification.ElixirSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CraftServiceImpl implements CraftService {
    private static final int MIN_RECIPE_SIZE = 2;
    private static final int MAX_RECIPE_SIZE = 3;

    private final ElixirRepository elixirRepository;
    private final UserInventoryRepository userInventoryRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    @Transactional
    public ElixirDto craftByIngredients(List<IngredientDto> ingredientDtoList) {
        if (ingredientDtoList.size() > MAX_RECIPE_SIZE || ingredientDtoList.size() < MIN_RECIPE_SIZE) {
            throw new IllegalArgumentException("Wrong recipe size! Elixir craft is failed!");
        }

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);

        List<Ingredient> ingredients = ingredientDtoList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        Elixir elixir = getElixirByIngredients(ingredients);

        if (isNull(elixir)) {
            throw new ElixirNotFoundException("There is no elixir with this recipe! Elixir craft is failed!");
        }
        if (!userInventory.getIngredients().containsAll(ingredients)) {
            throw new IllegalArgumentException("You don’t have needed ingredients! Elixir craft is failed!");
        }

        craftElixir(userInventory, ingredients, elixir);
        return convertToDto(elixir);
    }

    @Override
    @Transactional
    public ElixirDto craftByRecipe(ElixirDto elixirDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);

        Elixir elixir = convertToEntity(elixirDto);

        if (isNull(elixir)) {
            throw new ElixirNotFoundException("There is no elixir with this recipe! Elixir craft is failed!");
        }
        if (!userInventory.getIngredients().containsAll(elixir.getIngredients())) {
            throw new IllegalArgumentException("You don’t have needed ingredients! Elixir craft is failed!");
        }

        craftElixir(userInventory, elixir.getIngredients(), elixir);
        return convertToDto(elixir);
    }

    private Elixir getElixirByIngredients(List<Ingredient> ingredients) {
        Specification<Elixir> specification = Specification.where(null);
        ingredients.forEach(i -> specification.and(ElixirSpecification.filterByIngredient(i)));

        List<Elixir> elixirs = elixirRepository.findAll(specification);
        return elixirs.stream()
                .filter(item -> areListsEqual(item.getIngredients(), ingredients))
                .findFirst().orElse(null);
    }

    private boolean areListsEqual(List<Ingredient> list1, List<Ingredient> list2) {
        List<Ingredient> sortedList1 = list1.stream()
                .sorted(Comparator.comparing(Ingredient::getName))
                .collect(Collectors.toList());
        List<Ingredient> sortedList2 = list2.stream()
                .sorted(Comparator.comparing(Ingredient::getName))
                .collect(Collectors.toList());
        return sortedList1.equals(sortedList2);
    }

    private void craftElixir(UserInventory userInventory, Collection<Ingredient> ingredients, Elixir elixir) {
        Random random = new Random();
        ingredients.forEach(
                item -> {
                    if (random.nextInt(100) <= item.getType().getConsumeProbability())
                        userInventory.getIngredients().remove(item);
                }
        );
        userInventory.getElixirs().add(elixir);
    }

    private Ingredient convertToEntity(IngredientDto ingredientDto) {
        return ingredientRepository.getIngredientByNameAndTypeAndCost(
                ingredientDto.getName(), ingredientDto.getType(), ingredientDto.getCost());
    }

    private Elixir convertToEntity(ElixirDto elixirDto) {
        return elixirRepository.getElixirByName(elixirDto.getName());
    }

    private ElixirDto convertToDto(Elixir elixir) {
        return new ElixirDto(elixir.getName(), elixir.getCost(), elixir.getLevel());
    }
}
