package com.example.timariaproject.DTOs;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
