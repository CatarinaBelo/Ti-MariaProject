package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipoproduto")
@Getter
@Setter
public class Tipoproduto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idsubcategoria")
    private Subcategoria subcategoria;

    private String nometipoproduto;
}
