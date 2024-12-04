package com.example.timariaproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table
@Getter
@Setter
public class Utilizador {

    @Id
    private Integer id;
    private String nome;
    private String email;
    private BigInteger telefone;
    private String fotoperfil;
    private Integer localizacao;

}
