package com.example.timariaproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class UserEditDTO {

    private String nome;
    private BigInteger telefone;
    private String moradafiscal;
    private String fotoperfil;
    private String descricao;

}
