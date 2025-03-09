package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.SubcategoriaDTO;
import com.example.timariaproject.DTOs.UnidadesmedidaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unidadesmedida")
@Getter
@Setter
public class Unidadesmedida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nomeunidademedida;

    public UnidadesmedidaDTO toDto() {
        return UnidadesmedidaDTO.builder()
                .nomeunidademedida(this.nomeunidademedida)
                .build();
    }
}
