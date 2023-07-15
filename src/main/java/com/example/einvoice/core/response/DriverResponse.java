package com.example.einvoice.core.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DriverResponse {
    private int id;
    private int truckId;
    private int contactId;
    private BigDecimal salary;
}
