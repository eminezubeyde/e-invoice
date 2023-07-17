package com.example.einvoice.model;

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
    // TODO driver tablosu yok olması gerekir user sınıfı sadece inherit edilmiş bir sınıf olacak asıl tablo driver olacak.
    // TODO mapperSuperClass
    private BigDecimal salary;

    @OneToOne
    private Truck truck;

    @OneToMany(mappedBy = "driver")
    private List<Bonus> bonuses;


}
