package com.example.einvoice.core.requests.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CreateInvoiceRequest {
    private int truckId;
    private int companyId;
    private String containerNumber;
    private boolean isActive;
    private String fromCity;
    private String toCity;
    private BigDecimal amount;
    @JsonFormat
    private LocalDateTime processTime;
    private BigDecimal kdvRate;
}
