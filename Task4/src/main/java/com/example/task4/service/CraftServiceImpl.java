package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.UserInventory;
import com.example.task4.repository.ElixirRepository;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.UserInventoryRepository;
import com.example.task4.repository.specification.ElixirSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

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
    public boolean craftByIngredients(List<IngredientDto> ingredientDtoList) {
        if (ingredientDtoList.size() > MAX_RECIPE_SIZE || ingredientDtoList.size() < MIN_RECIPE_SIZE) {
            return false;
        }

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);

        List<Ingredient> ingredients = ingredientDtoList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        Specification<Elixir> specification = Specification
                .where(ElixirSpecification.filterByIngredient(ingredients.get(0)))
                .and(ElixirSpecification.filterByIngredient(ingredients.get(1)))
                .and(ElixirSpecification.filterByIngredient(ingredients.size() > 2 ? ingredients.get(2) : null));

        List<Elixir> elixirs = elixirRepository.findAll(specification);
        Elixir elixir = elixirs.stream()
                .filter(item -> areCollectionsEqual(item.getIngredients(), ingredients))
                .findFirst().orElse(null);

        boolean canCraft = nonNull(elixir) && userInventory.getIngredients().containsAll(ingredients);

        if (canCraft) {
            Random random = new Random();
            ingredients.forEach(
                    item -> {
                        if (random.nextInt(100) <= item.getType().getConsumeProbability())
                            userInventory.getIngredients().remove(item);
                    }
            );
            userInventory.getElixirs().add(elixir);
        }
        return canCraft;
    }

    @Override
    @Transactional
    public boolean craftByRecipe(ElixirDto elixirDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInventory userInventory = userInventoryRepository.getByUserEmail(userEmail);

        Elixir elixir = convertToEntity(elixirDto);

        boolean canCraft = nonNull(elixir) && userInventory.getIngredients().containsAll(elixir.getIngredients());

        if (canCraft) {
            Random random = new Random();
            elixir.getIngredients().forEach(
                    item -> {
                        if (random.nextInt(100) <= item.getType().getConsumeProbability())
                            userInventory.getIngredients().remove(item);
                    }
            );
            userInventory.getElixirs().add(elixir);
        }
        return canCraft;
    }

    private boolean areCollectionsEqual(Set<Ingredient> set, List<Ingredient> list) {
        return set.size() == list.size() && set.containsAll(list) && list.containsAll(set);
    }

    private Ingredient convertToEntity(IngredientDto ingredientDto) {
        return ingredientRepository.getIngredientByName(ingredientDto.getName());
    }

    private Elixir convertToEntity(ElixirDto elixirDto) {
        return elixirRepository.getElixirByName(elixirDto.getName());
    }
}
