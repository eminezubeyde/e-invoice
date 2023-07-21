package com.example.einvoice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
