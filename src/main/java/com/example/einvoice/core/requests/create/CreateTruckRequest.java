package com.example.einvoice.core.requests.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTruckRequest {

    private String plate;
    private String model;
    private String brand;
}
