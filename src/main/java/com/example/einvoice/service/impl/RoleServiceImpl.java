package com.example.einvoice.service.impl;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.entity.Role;
import com.example.einvoice.repository.RoleRepository;
import com.example.einvoice.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void create(Role role) throws AlreadyExistsException {
        log.info("role create method started with request : " + role);
        if (roleRepository.existsByName(role.getName())) {
            throw new AlreadyExistsException("already exist role " + role);
        }
        roleRepository.save(role);

    }

    @Override
    public Optional<Role> getByName(String name) throws EntityNotFoundException {
        return roleRepository.findByName(name);

    }

}
