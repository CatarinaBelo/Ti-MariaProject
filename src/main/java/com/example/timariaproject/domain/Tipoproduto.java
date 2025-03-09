package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.SubcategoriaDTO;
import com.example.timariaproject.DTOs.TipoprodutoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipoproduto")
@Getter
@Setter
public class Tipoproduto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idsubcategoria")
    private Subcategoria subcategoria;

    private String nometipoproduto;

    public TipoprodutoDTO toDto() {
        return TipoprodutoDTO.builder()
                .subcategoria(this.subcategoria != null ? this.subcategoria.toDto() : null)
                .nometipoproduto(this.nometipoproduto)
                .build();
    }
}
