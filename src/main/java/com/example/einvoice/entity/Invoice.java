package com.example.einvoice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String containerNumber;// 12 haneli olacak
    private boolean isActive; // kesildi mi
    private String fromCity;//nereden
    private String toCity; //nereye
    private BigDecimal amount;
    private LocalDateTime processTime;
    private BigDecimal kdvRate;
    private BigDecimal totalAmount;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Company company;

}
