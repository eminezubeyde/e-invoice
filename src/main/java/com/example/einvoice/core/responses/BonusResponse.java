package com.example.einvoice.core.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BonusResponse {
    private int id;
    private int driverId;
    private BigDecimal amount;
    private String fromCity;
    private String toCity;
    private LocalDateTime processTime;
}
