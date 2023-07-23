package com.example.einvoice.repository;

import com.example.einvoice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByTaxNumber(String taxNumber);//todo uniqe olmalı hatası
    Company findByName(String name);

}
