package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usercredentials")
@Getter
@Setter
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idutilizador;
    private String email;
    private String password;
}
