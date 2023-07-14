package com.example.einvoice.repository;

import com.example.einvoice.model.Company;
import com.example.einvoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
