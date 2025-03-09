package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.LocalizacaoDTO;
import com.example.timariaproject.DTOs.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
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

    public UserDTO toDto() {
        var dto = new UserDTO();
        try {
            BeanUtils.copyProperties(dto,this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }
}
