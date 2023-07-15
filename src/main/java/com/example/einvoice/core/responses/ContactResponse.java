package com.example.einvoice.core.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponse {
    private int id;
    private String telephoneNumber;
    private String address;
}
