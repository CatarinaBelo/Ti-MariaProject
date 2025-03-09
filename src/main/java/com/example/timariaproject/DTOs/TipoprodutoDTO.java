package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Subcategoria;
import com.example.timariaproject.domain.Tipoproduto;
import com.example.timariaproject.domain.Utilizador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TipoprodutoDTO {
    private Integer id;
    private SubcategoriaDTO subcategoria;
    private String nometipoproduto;

    public Tipoproduto toEntity(){
        Tipoproduto tipoproduto = new Tipoproduto();
        tipoproduto.setId(this.id);
        tipoproduto.setSubcategoria(this.subcategoria != null ? this.subcategoria.toEntity() : null);
        tipoproduto.setNometipoproduto(this.nometipoproduto);
        return tipoproduto;
    }

    public Tipoproduto toIdEntity(){
        Tipoproduto tipoproduto = new Tipoproduto();
        tipoproduto.setId(this.id);
        return tipoproduto;
    }
}
