package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Concelho;
import com.example.timariaproject.domain.Freguesia;
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
public class FreguesiaDTO {
    private Integer id;
    private String nomefreguesia;
    private ConcelhoDTO concelho;

    public Freguesia toEntity(){
        Freguesia freguesia = new Freguesia();
        freguesia.setId(this.id);
        freguesia.setConcelho(this.concelho != null ? this.concelho.toEntity() : null);
        freguesia.setNomefreguesia(this.nomefreguesia);
        return freguesia;
    }

    public Freguesia toIdEntity(){
        Freguesia freguesia = new Freguesia();
        freguesia.setId(this.id);
        return freguesia;
    }
}
