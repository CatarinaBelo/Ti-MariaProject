package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Tipoanuncio;
import com.example.timariaproject.domain.Tipoproduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TipoanuncioDTO {
    private Integer id;
    private String tipo;

    public Tipoanuncio toIdEntity(){
        Tipoanuncio tipoanuncio = new Tipoanuncio();
        tipoanuncio.setId(this.id);
        return tipoanuncio;
    }
}
