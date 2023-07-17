package com.example.einvoice.core.requests.update;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class UpdateInvoiceRequest {
    private int truckId;
    private int companyId;
    private String containerNumber;
    private boolean isCompleted;
    private String fromCity;
    private String toCity;
    private BigDecimal amount;
    private Date processTime;
    private int amountKDV;
}
