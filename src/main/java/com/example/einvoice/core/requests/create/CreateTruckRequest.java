package com.example.einvoice.core.requests.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateTruckRequest {

    @NotBlank(message = "{truck.validation.plate.notBlank}")
    private String plate;
    private String model;
    private String brand;
}
