package com.example.einvoice.model;

import jakarta.persistence.*;
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
    private BigDecimal amountKDV;
    private BigDecimal netAmount;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Company company;

    public BigDecimal netAmount(BigDecimal amount, BigDecimal amountKDV) {
        return ((amount.multiply(amountKDV)).divide(BigDecimal.valueOf(100))).add(amount);
    }


}
