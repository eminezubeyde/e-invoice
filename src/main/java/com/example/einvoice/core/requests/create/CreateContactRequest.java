package com.example.einvoice.core.requests.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CreateContactRequest {
    @NotBlank(message = "{contact.validation.telephoneNumber.notBlank}")
    @Pattern(regexp = "^[0-9]{10}$", message = "{contact.validation.telephoneNumber.invalidFormat}")
    private String telephoneNumber;

    private String address;
}
