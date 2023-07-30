package com.example.einvoice.repository;


import com.example.einvoice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByIdentityNumber(String identity);

    boolean existsByName(String name);
}
