package com.example.timariaproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String nome;
    private String email;
    private BigInteger telefone;
    private BigInteger nif;
    private String tipoutilizador;
    private String moradafiscal;
    private String fotoperfil;
    private String descricao;
}
