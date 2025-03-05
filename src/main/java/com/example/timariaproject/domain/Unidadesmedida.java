package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unidadesmedida")
@Getter
@Setter
public class Unidadesmedida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nomeunidademedida;
}
