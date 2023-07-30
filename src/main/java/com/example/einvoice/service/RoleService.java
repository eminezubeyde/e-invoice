package com.example.einvoice.service;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.entity.Role;

import java.util.Optional;

public interface RoleService {
    void create(Role role) throws AlreadyExistsException;

    Optional<Role> getByName(String name) throws EntityNotFoundException;
}
