package com.example.einvoice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Driver extends User {

    private BigDecimal salary;

    @OneToOne
    private Truck truck;

    @OneToMany(mappedBy = "driver",fetch = FetchType.LAZY)
    private List<Bonus> bonuses;


}
