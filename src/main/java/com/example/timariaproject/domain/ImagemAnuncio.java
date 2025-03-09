package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.ImagemAnuncioDTO;
import com.example.timariaproject.DTOs.TagDTO;
import com.example.timariaproject.domain.interfaces.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@Entity
@Table(name = "imagemanuncio")
@Getter
@Setter
public class ImagemAnuncio implements IEntity<ImagemAnuncioDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idanuncio", nullable = false)
    private Anuncio anuncio; // FK para Anuncio

    private String imagem;

    @Override
    public ImagemAnuncioDTO toDto() {
        return ImagemAnuncioDTO.builder()
                .imagem(this.imagem)
                .build();
    }
}
