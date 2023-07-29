package com.example.einvoice.service;

import com.example.einvoice.core.exception.GeneralException;
import com.example.einvoice.entity.Role;
import com.example.einvoice.entity.User;
import com.example.einvoice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<String> getUserRoles(String identity) throws GeneralException {
        Optional<User> optionalUser = userRepository.findByIdentity(identity);
        if (optionalUser.isEmpty()){
            throw new GeneralException("identity not correct.");
        }
        return optionalUser.get().getRoleList().stream().map(Role::getName).collect(Collectors.toList());
    }
}
