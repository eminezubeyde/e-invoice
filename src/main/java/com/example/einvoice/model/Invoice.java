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
    private String CompanyName;//ilişkisel yap
    private boolean isCompleted;
    private BigDecimal amount;
    private Date processTime;
    private int amountKDV;

    @ManyToOne
    private Truck truck;


}
