package com.example.einvoice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")//TODO doğru çalışmıyor
    private LocalDateTime processTime;
    private BigDecimal kdvRate;
    private BigDecimal totalAmount;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Company company;

}
