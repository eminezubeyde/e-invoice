package com.example.einvoice.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class InvoiceDto {
    private int id;
    private int companyId;
    private int truckId;
    private String containerNumber;
    private boolean isCompleted;
    private String fromCity;
    private String toCity;
    private BigDecimal amount;
    private Date processTime;
    private BigDecimal amountKDV;
    private BigDecimal netAmount;

}