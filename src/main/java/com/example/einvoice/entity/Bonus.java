package com.example.einvoice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal amount=BigDecimal.valueOf(300);
    private String fromCity;//nereden
    private String toCity; //nereye
    private LocalDateTime processTime;

    @ManyToOne
    private Driver driver;

}
