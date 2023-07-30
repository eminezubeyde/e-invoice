package com.example.einvoice.repository;

import com.example.einvoice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DriverRepository extends JpaRepository<Driver,Integer> {
    boolean existsByIdentityNumber(String identityNumber);
}
