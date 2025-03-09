package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Concelho;
import com.example.timariaproject.domain.Distrito;
import com.example.timariaproject.domain.Tipoproduto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ConcelhoDTO {
    private Integer id;
    private String nomeconcelho;
    private DistritoDTO distrito;

    public Concelho toEntity(){
        Concelho concelho = new Concelho();
        concelho.setId(this.id);
        concelho.setDistrito(this.distrito != null ? this.distrito.toIdEntity() : null);
        concelho.setNomeconcelho(this.nomeconcelho);
        return concelho;
    }

    public Concelho toIdEntity(){
        Concelho concelho = new Concelho();
        concelho.setId(this.id);
        return concelho;
    }
}
