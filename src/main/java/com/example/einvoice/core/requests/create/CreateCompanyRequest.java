package com.example.einvoice.core.requests.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCompanyRequest {
    @NotBlank(message = "{truck.validation.plate.notBlank}")
    private String name;
    private String taxNumber;
    private CreateContactRequest contactRequest;
}
