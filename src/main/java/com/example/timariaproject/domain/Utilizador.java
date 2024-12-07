package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;

@Entity
@Table(name = "utilizador")
@Getter
@Setter
public class Utilizador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String email;
    private String tipoutilizador;
    private BigInteger telefone;
    private BigInteger nif;
    private String moradafiscal;
    private String fotoperfil;
    private Integer localizacao;
    private Boolean notificacao;
    private String descricao;
}
