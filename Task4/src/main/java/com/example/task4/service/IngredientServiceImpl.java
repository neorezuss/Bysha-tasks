package com.example.task4.service;

import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.IngredientType;
import com.example.task4.entity.User;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final String DEFAULT_SORTING = "name";

    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<IngredientDto> getUserIngredients(String userEmail, String name, IngredientType type, Integer costGT, Integer costLT, String sortBy) {
        List<Ingredient> filteredIngredients =
                ingredientRepository.findIngredientsByUsersEmailAndByNameAndByTypeAndByCost(userEmail, name, type, costGT, costLT, getSort(sortBy));
        return filteredIngredients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean buyIngredient(String userEmail, IngredientDto ingredientDto) {
        User user = userRepository.getUserByEmail(userEmail);
        Ingredient ingredient = ingredientRepository.getIngredientByName(ingredientDto.getName());

        boolean canBuy = user.getCoins() >= ingredient.getCost();
        if (canBuy) {
            user.setCoins(user.getCoins() - ingredient.getCost());
            user.getIngredients().add(ingredient);
        }
        return canBuy;
    }

    @Override
    @Transactional
    public boolean sellIngredient(String userEmail, IngredientDto ingredientDto) {
        User user = userRepository.getUserByEmail(userEmail);
        Ingredient ingredient = ingredientRepository.getIngredientByName(ingredientDto.getName());

        boolean canSell = user.getIngredients().remove(ingredient);
        if (canSell) {
            user.setCoins(user.getCoins() + ingredient.getCost());
        }
        return canSell;
    }

    private IngredientDto convertToDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getName(), ingredient.getType(), ingredient.getCost());
    }

    private Sort getSort(String sortString) {
        Sort sort;
        try {
            switch (sortString) {
                case "name":
                    sort = Sort.by("name");
                    break;
                case "type":
                    sort = Sort.by("type");
                    break;
                case "cost":
                    sort = Sort.by("cost");
                    break;
                case "name desc":
                    sort = Sort.by("name").descending();
                    break;
                case "type desc":
                    sort = Sort.by("type").descending();
                    break;
                case "cost desc":
                    sort = Sort.by("cost").descending();
                    break;
                default:
                    sort = Sort.by(DEFAULT_SORTING);
                    break;
            }
        } catch (NullPointerException e) {
            sort = Sort.by(DEFAULT_SORTING);
        }
        return sort;
    }

}
