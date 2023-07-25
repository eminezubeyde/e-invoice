package com.example.einvoice.repository;

import com.example.einvoice.entity.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByProcessTimeBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Invoice> findByProcessTimeBetweenAndCompanyName(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable, String companyName);

    List<Invoice> findByProcessTimeBetweenAndTruckPlate(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable, String truckPlate);

    List<Invoice> findByProcessTimeBetweenAndTruckPlateAndCompanyName(LocalDateTime startDate, LocalDateTime endDate,
                                                                      Pageable pageable, String companyName, String truckPlate);

    List<Invoice> findByTotalAmountGreaterThan(BigDecimal minTotalAmount);

    List<Invoice> findByTotalAmountLessThan(BigDecimal maxTotalAmount);

}
