package com.example.einvoice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public BigDecimal netAmountCalculator(BigDecimal amount, BigDecimal kdvRate) {
        return ((amount.multiply(kdvRate)).divide(BigDecimal.valueOf(100))).add(amount);
    }

    public void setNetAmount() {
        this.totalAmount = netAmountCalculator(amount, kdvRate);
    }
}
