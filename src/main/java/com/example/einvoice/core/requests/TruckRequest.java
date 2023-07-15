package com.example.einvoice.core.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckRequest {
    private int driverId;
    private String plate;
    private String model;
    private String brand;
}
