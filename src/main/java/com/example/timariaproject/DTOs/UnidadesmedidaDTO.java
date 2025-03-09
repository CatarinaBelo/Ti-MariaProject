package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Tipoproduto;
import com.example.timariaproject.domain.Unidadesmedida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UnidadesmedidaDTO {
    private Integer id;
    private String nomeunidademedida;

    public Unidadesmedida toEntity(){
        Unidadesmedida unidadesmedida = new Unidadesmedida();
        unidadesmedida.setId(this.id);
        unidadesmedida.setNomeunidademedida(this.nomeunidademedida);
        return unidadesmedida;
    }

    public Unidadesmedida toIdEntity(){
        Unidadesmedida unidadesmedida = new Unidadesmedida();
        unidadesmedida.setId(this.id);
        return unidadesmedida;
    }
}
