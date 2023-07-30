package com.example.einvoice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
