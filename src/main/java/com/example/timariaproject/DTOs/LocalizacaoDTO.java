package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Localizacao;
import com.example.timariaproject.domain.Tipoproduto;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalizacaoDTO {
    private Integer id;
    private LocalDateTime dataatualizacao;
    private Integer latitude;
    private Integer longitude;
    private String endereco;
    private DistritoDTO distrito;
    private ConcelhoDTO concelho;
    private FreguesiaDTO freguesia;

    public Localizacao toEntity(){
        Localizacao localizacao = new Localizacao();
        localizacao.setId(this.id);
        localizacao.setDistrito(this.distrito != null ? this.distrito.toIdEntity() : null);
        localizacao.setDataatualizacao(this.dataatualizacao);
        localizacao.setLatitude(this.latitude);
        localizacao.setLongitude(this.longitude);
        localizacao.setEndereco(this.endereco);
        localizacao.setConcelho(this.concelho != null ? this.concelho.toIdEntity() : null);
        localizacao.setFreguesia(this.freguesia != null ? this.freguesia.toIdEntity() : null);
        return localizacao;
    }

    public Localizacao toIdEntity(){
        Localizacao loc = new Localizacao();
        loc.setId(this.id);
        return loc;
    }
}
