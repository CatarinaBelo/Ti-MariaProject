package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.TipoanuncioDTO;
import com.example.timariaproject.DTOs.TipoprodutoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipoanuncio")
@Getter
@Setter
public class Tipoanuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tipo;

    public TipoanuncioDTO toDto() {
        return TipoanuncioDTO.builder()
                .id(this.id)
                .tipo(this.tipo)
                .build();
    }
}
