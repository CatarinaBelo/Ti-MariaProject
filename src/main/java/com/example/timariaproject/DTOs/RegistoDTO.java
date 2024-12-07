package com.example.timariaproject.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
public class RegistoDTO {
    private String nome;
    private String email;
    private BigInteger telefone;
    private BigInteger nif;
    private String tipoutilizador;
    private String moradafiscal;
    private String password;
    private String confirmpassword;
}
