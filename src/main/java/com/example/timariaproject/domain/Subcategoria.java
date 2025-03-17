package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.DTOs.SubcategoriaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subcategoria")
@Getter
@Setter
public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria; // FK para Categoria

    private String nomesubcategoria;

    public SubcategoriaDTO toDto() {
        return SubcategoriaDTO.builder()
                .id(this.id)
                .categoria(this.categoria != null ? this.categoria.toDto() : null)
                .nomesubcategoria(this.nomesubcategoria)
                .build();
    }

}
