package com.example.einvoice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Driver extends User {
    private BigDecimal salary;

    @OneToOne
    private Truck truck;


}
