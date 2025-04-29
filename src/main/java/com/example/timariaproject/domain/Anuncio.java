package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.DTOs.AnuncioEditDTO;
import com.example.timariaproject.domain.interfaces.IEntity;
import com.example.timariaproject.enums.EstadoEnum;
import com.example.timariaproject.enums.EstadoEnumConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "anuncio")
@Where(clause = "estado = 1")
@Getter
@Setter
public class Anuncio implements IEntity<AnuncioDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String descricao;
    private Double preco;
    private LocalDateTime datacriacao;
    private LocalDateTime dataatualizacao;

    @Convert(converter = EstadoEnumConverter.class)
    private EstadoEnum estado;

    @ManyToOne
    @JoinColumn(name = "idtipoanuncio")
    private Tipoanuncio tipoanuncio;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idlocalizacao")
    private Localizacao localizacao;

    private String rotulopersonalizado;

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagemAnuncio> imagens; // Esta lista não representa um campo na BD, apenas a relação.

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anunciotag> anuncioTags;

    @PrePersist
    protected void onCreate() {
        this.datacriacao = LocalDateTime.now();  // Define a data de criação apenas na criação
        this.dataatualizacao = LocalDateTime.now(); // Define também a primeira atualização
        this.estado = EstadoEnum.ATIVO; // Define o estado inicial como ATIVO
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataatualizacao = LocalDateTime.now(); // Atualiza apenas na modificação do anúncio
    }

    @Override
    public AnuncioDTO toDto() {
        return AnuncioDTO.builder()
                .id(this.id)
                .titulo(this.titulo)
                .descricao(this.descricao)
                .preco(this.preco)
                .datacriacao(this.datacriacao)
                .dataatualizacao(this.dataatualizacao)
                .tipoanuncio(this.tipoanuncio != null ? this.tipoanuncio.toDto() : null)
                .utilizadorDTO(this.utilizador != null ? this.utilizador.toDto() : null)
                .categoria(this.categoria != null ? this.categoria.toDto() : null)
                .subcategoria(this.subcategoria != null ? this.subcategoria.toDto() : null)
                .tipoProduto(this.tipoProduto != null ? this.tipoProduto.toDto() : null)
                .stock(this.stock)
                .unidadesMedida(this.unidadesMedida != null ? this.unidadesMedida.toDto() : null)
                .imagens(this.imagens.stream().map(ImagemAnuncio::toDto).toList())
                .localizacao(this.localizacao != null ? this.localizacao.toDto() : null)
                .rotuloPersonalizado(this.rotulopersonalizado)
                .anuncioTags(this.anuncioTags.stream().map(Anunciotag::toDto).toList())
                .build();
    }


    public AnuncioEditDTO toEditDto() {
        return AnuncioEditDTO.builder()
                .titulo(this.titulo)
                .descricao(this.descricao)
                .preco(this.preco)
                .tipoanuncio(this.tipoanuncio != null ? this.tipoanuncio.toDto() : null)
                .categoria(this.categoria != null ? this.categoria.toDto() : null)
                .subcategoria(this.subcategoria != null ? this.subcategoria.toDto() : null)
                .tipoproduto(this.tipoProduto != null ? this.tipoProduto.toDto() : null)
                .stock(this.stock)
                .unidadesMedida(this.unidadesMedida != null ? this.unidadesMedida.toDto() : null)
                .imagens(this.imagens.stream().map(ImagemAnuncio::toDto).toList())
                .localizacao(this.localizacao != null ? this.localizacao.toDto() : null)
                .rotulopersonalizado(this.rotulopersonalizado)
                .anuncioTags(this.anuncioTags.stream().map(Anunciotag::toDto).toList())
                .build();
    }
}
