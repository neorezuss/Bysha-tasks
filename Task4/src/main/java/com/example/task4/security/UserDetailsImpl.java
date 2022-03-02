package com.example.task4.security;

import com.example.task4.entity.Password;
import com.example.task4.entity.User;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Builder
public class UserDetailsImpl implements UserDetails {
    private String email;
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserDetails fromUserEntityToUserDetails(User user, Password password) {
        return UserDetailsImpl.builder()
                .email(user.getEmail())
                .password(password.getPassword())
                .enabled(user.isEnabled())
                .grantedAuthorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRoles().toString())))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
