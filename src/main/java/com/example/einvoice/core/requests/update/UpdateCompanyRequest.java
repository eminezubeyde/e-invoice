package com.example.einvoice.core.requests.update;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateCompanyRequest {
    private String name;
    private String taxNumber;

}
