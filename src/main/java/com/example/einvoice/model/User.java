package com.example.einvoice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "users")
@RequiredArgsConstructor
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String identityNumber;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Contact contact;
}
