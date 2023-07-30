package com.example.einvoice.core.security;

import com.example.einvoice.entity.Role;
import com.example.einvoice.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private final User user;

    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            return user.getRoles().stream().map(this::createSimpleGrantedAuthorities).collect(Collectors.toList());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getIdentityNumber();
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
        return true;
    }


    private SimpleGrantedAuthority createSimpleGrantedAuthorities(Role role) {
        return new SimpleGrantedAuthority(role.getName());
    }
}