package com.example.einvoice.config;

import com.example.einvoice.core.constant.security.Roles;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.entity.Role;
import com.example.einvoice.entity.User;
import com.example.einvoice.repository.RoleRepository;
import com.example.einvoice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    @Transactional
    public void initAdmin() throws EntityNotFoundException {
        PasswordEncoder passwordEncoder = passwordEncoder();
        if (!roleRepository.existsByName(Roles.ADMIN)) {
            Role role = new Role();
            role.setName(Roles.ADMIN);
            roleRepository.save(role);
        }
        if (!roleRepository.existsByName(Roles.DRIVER)) {
            Role role = new Role();
            role.setName(Roles.DRIVER);
            roleRepository.save(role);
        }


        if (!userRepository.existsByName(Roles.ADMIN)) {
            User user = new User();
            user.setName("ADMIN");
            user.setIdentityNumber("ADMIN");
            user.setPassword(passwordEncoder.encode("ADMIN"));
            Optional<Role> roleOptional = roleRepository.findByName(Roles.ADMIN);
            if (roleOptional.isPresent()) {
                user.setRoles(List.of(roleOptional.get()));
                userRepository.save(user);
            }
        }
    }

}
