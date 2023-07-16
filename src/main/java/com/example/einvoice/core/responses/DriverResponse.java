package com.example.einvoice.core.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DriverResponse {
    private int id;
    private int truckId;
    private int contactId;
    private String name;
    private String surname;
    private String identityNumber;
    private BigDecimal salary;
}
