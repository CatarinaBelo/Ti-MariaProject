package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilizador")
@Getter
@Setter
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String email;
    private String tipoutilizador;
    private BigInteger telefone;
    private BigInteger nif;
    private String moradafiscal;
    private String fotoperfil;

    @ManyToOne
    @JoinColumn(name = "localizacao")
    private Localizacao localizacao;

    private Boolean notificacao;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "favoritos",
            joinColumns = @JoinColumn(name = "idutilizador"),
            inverseJoinColumns = @JoinColumn(name = "idanuncio")
    )
    private List<Anuncio> favoritos = new ArrayList<>();

    // 🔁 Relações com postagens, comentários e likes
    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL)
    private List<Postagem> postagens = new ArrayList<>();

    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL)
    private List<LikePostagem> likes = new ArrayList<>();

    // ✅ Conversão para DTO
    public UserDTO toDto() {
        return UserDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .tipoutilizador(this.tipoutilizador)
                .telefone(this.telefone)
                .nif(this.nif)
                .moradafiscal(this.moradafiscal)
                .fotoperfil(this.fotoperfil)
                .descricao(this.descricao)
                .build();
    }
}
