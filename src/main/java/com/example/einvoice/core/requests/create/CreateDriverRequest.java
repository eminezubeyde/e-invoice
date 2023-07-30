package com.example.einvoice.core.requests.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateDriverRequest {

    @NotBlank(message = "{driver.validation.name.notBlank}")
    private String name;

    @NotBlank(message = "{driver.validation.surname.notBlank}")
    private String surname;

    @NotBlank(message = "{driver.validation.identityNumber.notBlank}")
    @Pattern(regexp = "^[0-9]{11}$", message = "{driver.validation.identityNumber.invalidFormat}")
    private String identityNumber;

    @Positive(message = "{driver.validation.salary.positive}")
    private BigDecimal salary;

    private String password;

    private CreateContactRequest contact;
    private int truckId;
}
