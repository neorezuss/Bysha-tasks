package com.example.task4.service;

import com.example.task4.dto.RegistrationDto;
import com.example.task4.entity.*;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.PasswordRepository;
import com.example.task4.repository.RoleRepository;
import com.example.task4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private static final boolean DEFAULT_ENABLED_STATUS = true;
    private static final int DEFAULT_COINS_COUNT = 100;
    private static List<Ingredient> startIngredients;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        startIngredients = List.of(
                ingredientRepository.getById(1L),
                ingredientRepository.getById(2L)
        );
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        Role defaultRole = roleRepository.getRoleByName(RoleEnum.ROLE_USER);
        User user = User.builder()
                .name(registrationDto.getName())
                .email(registrationDto.getEmail())
                .enabled(DEFAULT_ENABLED_STATUS)
                .role(defaultRole)
                .coins(DEFAULT_COINS_COUNT)
                .ingredients(startIngredients)
                .build();
        Password password = Password.builder()
                .user(user)
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .build();
        passwordRepository.save(password);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Password userPassword = passwordRepository.findByUser_Email(email).get();
            if (passwordEncoder.matches(password, userPassword.getPassword())) {
                return user.get();
            }
        }
        return null;
    }
}
