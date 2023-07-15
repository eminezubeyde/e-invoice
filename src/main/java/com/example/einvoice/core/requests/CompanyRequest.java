package com.example.einvoice.core.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {
    private String name;
    private String taxNumber;
    private int contactId;
}
