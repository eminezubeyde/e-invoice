package com.example.einvoice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String containerNumber;// 12 haneli olacak
    private boolean isCompleted;
    private String fromCity;//nereden
    private String toCity; //nereye
    private BigDecimal amount;
    private Date processTime;
    private int amountKDV;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Company company;


}
