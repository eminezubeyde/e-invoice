package com.example.einvoice.core.requests.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCompanyRequest {
    private String name;
    private String taxNumber;
    private int contactId;
}
