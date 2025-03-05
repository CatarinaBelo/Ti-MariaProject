package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
