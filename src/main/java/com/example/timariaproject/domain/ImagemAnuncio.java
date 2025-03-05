package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "imagemanuncio")
@Getter
@Setter
public class ImagemAnuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idanuncio", nullable = false)
    private Anuncio anuncio; // FK para Anuncio

    private String imagem;
}
