package com.example.einvoice.repository;

import com.example.einvoice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
    boolean existsByIdentity(String identityNumber);
}
