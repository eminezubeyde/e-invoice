package com.example.einvoice.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
    private int id;
    private String name;
    private String taxNumber;
    private int contactId;

}
