package com.example.einvoice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "users")
@RequiredArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String identity;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Contact contact;

    private String password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Role> roleList;
}
