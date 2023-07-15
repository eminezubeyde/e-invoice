package com.example.einvoice.core.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponse {
    private int id;
    private String name;
    private String taxNumber;
}
