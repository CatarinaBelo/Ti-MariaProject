package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subcategoria")
@Getter
@Setter
public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria; // FK para Categoria

    private String nomesubcategoria;

}
