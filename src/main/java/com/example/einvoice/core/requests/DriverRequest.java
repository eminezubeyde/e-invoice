package com.example.einvoice.core.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DriverRequest {
    private String name;
    private String surname;
    private String identityNumber;
    private BigDecimal salary;
    private int contactId;
}
