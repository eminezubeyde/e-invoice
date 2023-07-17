package com.example.einvoice.core.requests.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequest {
    private String telephoneNumber;
    private String address;
}
