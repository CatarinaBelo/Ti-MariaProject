package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nomeproduto;
    private String tipoproduto;
    private Integer stock;
    private Integer anuncio;
    private Integer categoriaproduto;
    private String tags;
    private Integer idsubcategoria;

}
