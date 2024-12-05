package com.example.timariaproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usercredentials")
@Getter
@Setter
public class UserCredentials {

    @Id
    private Integer id;
    private Integer idutilizador;
    private String email;
    private String password;
}
