package com.example.einvoice.repository;

import com.example.einvoice.model.Bonus;
import com.example.einvoice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonusRepository extends JpaRepository<Bonus,Integer> {
}
