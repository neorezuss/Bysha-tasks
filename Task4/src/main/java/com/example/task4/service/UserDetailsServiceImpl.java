package com.example.task4.service;

import com.example.task4.entity.Password;
import com.example.task4.entity.User;
import com.example.task4.repository.PasswordRepository;
import com.example.task4.repository.UserRepository;
import com.example.task4.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email));
        Password password = passwordRepository.findByUserEmail(email);
        return UserDetailsImpl.fromUserEntityToUserDetails(user, password);
    }
}
