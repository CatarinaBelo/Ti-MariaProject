package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Distrito;
import com.example.timariaproject.domain.Tipoproduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DistritoDTO {
    private Integer id;
    private String nomedistrito;

    public Distrito toEntity(){
        Distrito distrito = new Distrito();
        distrito.setId(this.id);
        distrito.setNomedistrito(this.nomedistrito);
        return distrito;
    }

    public Distrito toIdEntity(){
        Distrito distrito = new Distrito();
        distrito.setId(this.id);
        return distrito;
    }
}
