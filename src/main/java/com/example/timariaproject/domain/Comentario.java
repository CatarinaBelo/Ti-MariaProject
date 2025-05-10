package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "autor", nullable = false)
    private Utilizador autor;


    @ManyToOne
    @JoinColumn(name = "postagem_id", nullable = false)
    private Postagem postagem;
}
