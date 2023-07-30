package com.example.einvoice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
