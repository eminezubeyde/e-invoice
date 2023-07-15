package com.example.einvoice.core.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckResponse {
    private int id;
    private int driverId;
    private String plate;
    private String model;
    private String brand;
}
