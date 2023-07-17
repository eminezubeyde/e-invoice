package com.example.einvoice.core.requests.create;

import com.example.einvoice.core.requests.create.CreateContactRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateDriverRequest {
    private String name;
    private String surname;
    private String identityNumber;
    private BigDecimal salary;
    private CreateContactRequest contact;
    private int truckId;
}
