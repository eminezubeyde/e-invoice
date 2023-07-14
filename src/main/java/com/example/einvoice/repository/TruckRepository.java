package com.example.einvoice.repository;

import com.example.einvoice.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck,Integer> {
}
