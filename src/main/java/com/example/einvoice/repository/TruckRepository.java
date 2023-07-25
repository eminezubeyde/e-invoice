package com.example.einvoice.repository;

import com.example.einvoice.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck,Integer> {
    Truck findByPlate(String plate);

    boolean existsByPlate(String plate);
}
