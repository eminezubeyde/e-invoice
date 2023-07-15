package com.example.einvoice.core.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {
    private String telephoneNumber;
    private String address;
}
