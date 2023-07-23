package com.example.einvoice.repository;

import com.example.einvoice.entity.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

    List<Invoice> findByProcessTimeBetween(LocalDateTime startDate , LocalDateTime endDate, Pageable pageable);
    List<Invoice> findByProcessTimeBetweenAndCompanyName(LocalDateTime startDate , LocalDateTime endDate, Pageable pageable,String companyName);

}
