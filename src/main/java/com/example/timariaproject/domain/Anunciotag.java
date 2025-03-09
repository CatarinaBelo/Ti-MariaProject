package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.AnunciotagDTO;
import com.example.timariaproject.DTOs.ImagemAnuncioDTO;
import com.example.timariaproject.domain.interfaces.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@Entity
@Table(name = "anunciotag")
@Getter
@Setter
public class Anunciotag implements IEntity<AnunciotagDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idanuncio", nullable = false)
    private Anuncio anuncio;

    @ManyToOne
    @JoinColumn(name = "idtag", nullable = false)
    private Tag tag;

    @Override
    public AnunciotagDTO toDto() {
       return AnunciotagDTO.builder()
               .tag(this.tag != null ? this.tag.toDto() : null)
               .build();
    }
}
