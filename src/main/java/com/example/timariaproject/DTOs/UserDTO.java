package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Utilizador;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String nome;
    private String email;
    private BigInteger telefone;
    private BigInteger nif;
    private String tipoutilizador;
    private String moradafiscal;
    private String fotoperfil;
    private String descricao;

    public Utilizador toIdEntity(){
        Utilizador utilizador = new Utilizador();
        utilizador.setId(this.id);
        return utilizador;
    }
}
