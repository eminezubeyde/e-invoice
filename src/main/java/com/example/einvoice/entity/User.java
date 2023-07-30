package com.example.einvoice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String identityNumber;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Contact contact;
    private String password;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<Role> roles;
}
