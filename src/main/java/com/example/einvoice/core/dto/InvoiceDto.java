package com.example.einvoice.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class InvoiceDto {
    private int id;
    private int companyId;
    private int truckId;
    private String containerNumber;
    private boolean isActive;
    private String fromCity;
    private String toCity;
    private BigDecimal amount;
    private LocalDateTime processTime;
    private BigDecimal kdvRate;
    private BigDecimal totalAmount;

}
