package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.ImagemAnuncioDTO;
import com.example.timariaproject.DTOs.LocalizacaoDTO;
import com.example.timariaproject.domain.interfaces.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@Entity
@Table(name = "localizacao")
@Getter
@Setter
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataatualizacao;
    private Integer latitude;
    private Integer longitude;
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "iddistrito", nullable = false)
    private Distrito distrito;

    @ManyToOne
    @JoinColumn(name = "idconcelho", nullable = false)
    private Concelho concelho;

    @ManyToOne
    @JoinColumn(name = "idfreguesia", nullable = false)
    private Freguesia freguesia;


    public LocalizacaoDTO toDto() {
        return LocalizacaoDTO.builder()
                .dataatualizacao(this.dataatualizacao)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .endereco(this.endereco)
                .distrito(this.distrito != null ? this.distrito.toDto() : null)
                .concelho(this.concelho != null ? this.concelho.toDto() : null)
                .freguesia(this.freguesia != null ? this.freguesia.toDto() : null)
                .build();
    }
}
