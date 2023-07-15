package com.example.einvoice.core.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

public class BonusRequest {
    private BigDecimal amount;
    private String fromCity;
    private String toCity;
    private LocalDateTime processTime;
    private int driverId;
}
