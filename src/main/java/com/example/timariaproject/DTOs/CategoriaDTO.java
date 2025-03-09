package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.domain.Utilizador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private Integer id;
    private String nomecategoria;

    public Categoria toEntity(){
        Categoria categoria = new Categoria();
        categoria.setId(this.id);
        categoria.setNomecategoria(this.nomecategoria);
        return categoria;
    }

    public Categoria toIdEntity(){
        Categoria categoria = new Categoria();
        categoria.setId(this.id);
        return categoria;
    }
}
