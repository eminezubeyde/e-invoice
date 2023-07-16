package com.example.einvoice.repository;

import com.example.einvoice.model.Driver;
import com.example.einvoice.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
    boolean existsByIdentityNumber(String identityNumber);
}
