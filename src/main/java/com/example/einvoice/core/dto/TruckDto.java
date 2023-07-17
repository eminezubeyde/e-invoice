package com.example.einvoice.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckDto {
    private int id;
    private int driverId;
    private String plate;
    private String model;
    private String brand;
}
