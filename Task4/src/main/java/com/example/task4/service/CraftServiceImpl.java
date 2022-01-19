package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.User;
import com.example.task4.repository.ElixirRepository;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CraftServiceImpl implements CraftService {
    @Autowired
    private ElixirRepository elixirRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    @Transactional
    public boolean craftByIngredients(String userEmail, List<IngredientDto> ingredientDtoList) {
        User user = userRepository.getUserByEmail(userEmail);
        List<Ingredient> ingredients;
        try {
            ingredients = ingredientDtoList.stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            return false;
        }
        List<Elixir> elixirs = elixirRepository.findAll();
        Optional<Elixir> elixirOptional = elixirs.stream().filter(item -> areCollectionsEqual(item.getIngredients(), ingredients)).findFirst();
        boolean canCraft = elixirOptional.isPresent() && user.getIngredients().containsAll(ingredients);
        if (canCraft) {
            Random random = new Random();
            ingredients.forEach(
                    item -> {
                        if (random.nextInt(100) <= item.getType().getConsumeProbability())
                            user.getIngredients().remove(item);
                    }
            );
            user.getElixirs().add(elixirOptional.get());
        }
        return canCraft;
    }

    @Override
    @Transactional
    public boolean craftByRecipe(String userEmail, ElixirDto elixirDto) {
        User user = userRepository.getUserByEmail(userEmail);
        Elixir elixir;
        boolean canCraft;
        try {
            elixir = convertToEntity(elixirDto);
            canCraft = user.getIngredients().containsAll(elixir.getIngredients());
        } catch (NullPointerException e) {
            return false;
        }
        if (canCraft) {
            Random random = new Random();
            elixir.getIngredients().forEach(
                    item -> {
                        if (random.nextInt(100) <= item.getType().getConsumeProbability())
                            user.getIngredients().remove(item);
                    }
            );
            user.getElixirs().add(elixir);
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
