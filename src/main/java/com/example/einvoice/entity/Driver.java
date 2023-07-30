package com.example.einvoice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Inheritance(strategy = InheritanceType.JOINED)
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
