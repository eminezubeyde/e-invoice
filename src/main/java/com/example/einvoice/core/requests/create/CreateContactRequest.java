package com.example.einvoice.core.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequest {
    @NotBlank(message = "{contact.validation.telephoneNumber.notBlank}")
    @Pattern(regexp = "^[0-9]{10}$", message = "{contact.validation.telephoneNumber.invalidFormat}")
    private String telephoneNumber;

    private String address;
}
