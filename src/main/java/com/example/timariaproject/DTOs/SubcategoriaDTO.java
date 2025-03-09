package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.domain.Subcategoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SubcategoriaDTO {
    private Integer id;
    private CategoriaDTO categoria; // FK para Categoria
    private String nomesubcategoria;


    public Subcategoria toEntity(){
        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setId(this.id);
        subcategoria.setCategoria(this.categoria != null ? this.categoria.toEntity() : null);
        subcategoria.setNomesubcategoria(this.nomesubcategoria);
        return subcategoria;
    }


    public Subcategoria toIdEntity(){
        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setId(this.id);
        return subcategoria;
    }
}
