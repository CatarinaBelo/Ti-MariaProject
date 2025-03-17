package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.DTOs.CategoriaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categoria")
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nomecategoria;

    public CategoriaDTO toDto() {
        return CategoriaDTO.builder()
                .id(this.id)
                .nomecategoria(this.nomecategoria)
                .build();
    }

}
