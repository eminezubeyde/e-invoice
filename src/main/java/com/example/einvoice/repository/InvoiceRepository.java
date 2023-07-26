package com.example.einvoice.repository;

import com.example.einvoice.entity.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    //(:nameParam is null OR e.name = :nameParam)
    @Query(value = "SELECT i.id,i.container_number,i.is_active,i.from_city,i.to_city,amount,i.process_time,i.kdv_rate,i.total_amount,i.truck_id,i.company_id  FROM invoice as i inner join company as c on i.company_id=c.id inner join truck as t on i.truck_id=t.id \n" +
            "       WHERE i.process_time BETWEEN :startDate AND :endDate and (:companyName is null OR c.name = :companyName) and (:minTotalAmount is null OR i.total_amount >=  :minTotalAmount) and (:max is null OR i.total_amount <=  :max) and (:plate is null OR t.plate = :plate)", nativeQuery = true)
    List<Invoice> invoicesFilter(LocalDateTime startDate, LocalDateTime endDate, String companyName, String plate, BigDecimal minTotalAmount, BigDecimal max, Pageable pageable);

    List<Invoice> findByProcessTimeBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Invoice> findByProcessTimeBetweenAndCompanyName(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable, String companyName);

    List<Invoice> findByProcessTimeBetweenAndTruckPlate(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable, String truckPlate);

    List<Invoice> findByProcessTimeBetweenAndTruckPlateAndCompanyName(LocalDateTime startDate, LocalDateTime endDate,
                                                                      Pageable pageable, String companyName, String truckPlate);

    List<Invoice> findByTotalAmountGreaterThan(BigDecimal minTotalAmount);

    List<Invoice> findByTotalAmountLessThan(BigDecimal maxTotalAmount);

}
