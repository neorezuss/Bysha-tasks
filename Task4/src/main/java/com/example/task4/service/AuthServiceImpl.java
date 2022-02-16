package com.example.task4.service;

import com.example.task4.dto.LoginDto;
import com.example.task4.dto.RefreshTokenDto;
import com.example.task4.dto.RegistrationDto;
import com.example.task4.entity.Ingredient;
import com.example.task4.entity.Password;
import com.example.task4.entity.Role;
import com.example.task4.entity.RoleEnum;
import com.example.task4.entity.User;
import com.example.task4.entity.UserInventory;
import com.example.task4.repository.IngredientRepository;
import com.example.task4.repository.PasswordRepository;
import com.example.task4.repository.RoleRepository;
import com.example.task4.repository.UserInventoryRepository;
import com.example.task4.repository.UserRepository;
import com.example.task4.security.AuthResponse;
import com.example.task4.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final boolean DEFAULT_ENABLED_STATUS = true;
    private static final int DEFAULT_COINS_COUNT = 100;
    private static List<Ingredient> startIngredients;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserInventoryRepository userInventoryRepository;
    private final PasswordRepository passwordRepository;
    private final IngredientRepository ingredientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @PostConstruct
    public void init() {
        startIngredients = List.of(
                ingredientRepository.getById(1L),
                ingredientRepository.getById(2L)
        );
    }

    @Override
    public AuthResponse authenticateUser(LoginDto loginDto) {
        User user = findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        String accessToken = jwtProvider.generateAccessToken(user.getEmail(), user.getRoles());
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail());
        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public RegistrationDto registerUser(RegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Unable to create. Email is already taken!");
        }

        if (userRepository.existsByName(registrationDto.getName())) {
            throw new IllegalArgumentException("Unable to create. Name is already taken!");
        }

        saveUser(registrationDto);
        return registrationDto;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenDto refreshTokenDto) {
        jwtProvider.validateToken(refreshTokenDto.getRefreshToken());
        String email = jwtProvider.getEmailFromToken(refreshTokenDto.getRefreshToken());
        User user = findByEmail(email);
        String accessToken = jwtProvider.generateAccessToken(user.getEmail(), user.getRoles());
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail());
        return new AuthResponse(accessToken, refreshToken);
    }

    private void saveUser(RegistrationDto registrationDto) {
        Role defaultRole = roleRepository.getRoleByName(RoleEnum.ROLE_USER);

        User user = User.builder()
                .name(registrationDto.getName())
                .email(registrationDto.getEmail())
                .enabled(DEFAULT_ENABLED_STATUS)
                .role(defaultRole)
                .build();

        Password password = Password.builder()
                .user(user)
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .build();

        passwordRepository.save(password);

        UserInventory userInventory = UserInventory.builder()
                .user(user)
                .coins(DEFAULT_COINS_COUNT)
                .ingredients(startIngredients)
                .build();

        userInventoryRepository.save(userInventory);
    }

    private User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email));
        Password userPassword = passwordRepository.findByUserEmail(email);
        if (!passwordEncoder.matches(password, userPassword.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password!");
        }
        return user;
    }

    private User findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email));
        Password userPassword = passwordRepository.findByUserEmail(email);
        return user;
    }
}
