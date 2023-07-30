package com.example.einvoice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String plate;
    private String model;
    private String brand;

    @OneToOne
    private Driver driver;

    @OneToMany(mappedBy = "truck")
    private List<Invoice> invoiceList; // invoices


}
