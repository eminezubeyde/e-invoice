package com.example.einvoice.core.requests.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTruckRequest {

    @NotBlank(message = "{truck.validation.plate.notBlank}")
    private String plate;
    private String model;
    private String brand;
}
