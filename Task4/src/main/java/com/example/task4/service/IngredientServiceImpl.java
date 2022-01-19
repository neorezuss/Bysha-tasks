package com.example.task4.service;

import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.IngredientType;
import com.example.task4.entity.User;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.specification.IngredientSpecification;
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
public class IngredientServiceImpl implements IngredientService {
    private static final String DEFAULT_SORTING = "name";

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<IngredientDto> getUserIngredients(String name, IngredientType type, Integer costGT, Integer costLT, String sortBy, String sortDirection) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Sort.Direction direction = Sort.Direction.fromOptionalString(sortDirection).orElse(Sort.DEFAULT_DIRECTION);
        Specification<Ingredient> specification = Specification
                .where(IngredientSpecification.filterByUsersEmail(userEmail))
                .and(IngredientSpecification.filterByName(name))
                .and(IngredientSpecification.filterByType(type))
                .and(IngredientSpecification.filterByCostGT(costGT))
                .and(IngredientSpecification.filterByCostLT(costLT));
        List<Ingredient> filteredIngredients;
        try {
            filteredIngredients = ingredientRepository.findAll(specification, Sort.by(direction, sortBy));
        } catch (PropertyReferenceException | IllegalArgumentException e) {
            filteredIngredients = ingredientRepository.findAll(specification, Sort.by(DEFAULT_SORTING));
        }
        return filteredIngredients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean buyIngredient(IngredientDto ingredientDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
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
    public boolean sellIngredient(IngredientDto ingredientDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
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
}
