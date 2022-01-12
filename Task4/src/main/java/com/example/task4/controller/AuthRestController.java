package com.example.task4.controller;

import com.example.task4.dto.LoginDto;
import com.example.task4.dto.RegistrationDto;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.Role;
import com.example.task4.entity.User;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private static final boolean DEFAULT_ENABLED_STATUS = true;
    private static final int DEFAULT_COINS_COUNT = 100;
    private static List<Ingredient> startIngredients;

    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthRestController(UserRepository userRepository, IngredientRepository ingredientRepository,
                              AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        startIngredients = List.of(
                ingredientRepository.getById(1L),
                ingredientRepository.getById(2L)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User successfully logged in.", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByName(registrationDto.getName())) {
            return new ResponseEntity<>("Name is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEnabled(DEFAULT_ENABLED_STATUS);
        user.setRole(Role.ROLE_USER);
        user.setCoins(DEFAULT_COINS_COUNT);
        user.setIngredients(startIngredients);

        userRepository.save(user);

        return new ResponseEntity<>("User successfully registered", HttpStatus.OK);

    }
}
