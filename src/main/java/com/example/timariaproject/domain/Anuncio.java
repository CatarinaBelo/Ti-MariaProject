package com.example.timariaproject.domain;

import com.example.timariaproject.enums.EstadoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "anuncio")
@Getter
@Setter
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String descricao;
    private Double preco;
    private LocalDateTime datacriacao;
    private LocalDateTime dataatualizacao;

    private String tipo;

    private EstadoEnum estado;

    @ManyToOne
    @JoinColumn(name = "idutilizador")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria; // FK para Categoria

    @ManyToOne
    @JoinColumn(name = "idsubcategoria")
    private Subcategoria subcategoria;

    @ManyToOne
    @JoinColumn(name = "idtipoproduto")
    private Tipoproduto tipoProduto;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "idunidadesmedida")
    private Unidadesmedida unidadesMedida;

    @ManyToOne
    @JoinColumn(name = "idlocalizacao")
    private Localizacao localizacao;

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagemAnuncio> imagens; // Esta lista não representa um campo na BD, apenas a relação.

}
