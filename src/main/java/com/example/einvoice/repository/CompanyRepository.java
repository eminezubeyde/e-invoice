package com.example.einvoice.repository;

import com.example.einvoice.model.Company;
import com.example.einvoice.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByTaxNumber(String taxNumber);

}
