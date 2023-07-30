package com.example.einvoice.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String taxNumber;//vergi numarası

    @OneToMany(mappedBy = "company")
    private List<Invoice> invoiceList;

    @OneToOne
    private Contact contact;


}
