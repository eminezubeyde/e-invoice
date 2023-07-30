package com.example.einvoice.core.requests.create;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCompanyRequest {
    @NotBlank(message = "{truck.validation.plate.notBlank}")
    private String name;
    private String taxNumber;
    private CreateContactRequest contactRequest;
}
