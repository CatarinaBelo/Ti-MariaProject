package com.example.timariaproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "postagens")
@Getter
@Setter
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String conteudo;

    @Column(nullable = false)
    private String autor;  // Ou podes fazer relação com entidade Utilizador
}
