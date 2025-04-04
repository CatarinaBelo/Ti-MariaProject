package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.*;
import com.example.timariaproject.enums.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnuncioSaveDTO {
    private String titulo;
    private String descricao;
    private Double preco;
    private TipoanuncioDTO tipoanuncio;
    private UserDTO utilizador;
    private CategoriaDTO categoria;
    private SubcategoriaDTO subcategoria;
    private TipoprodutoDTO tipoproduto;
    private Integer stock;
    private UnidadesmedidaDTO unidadesMedida;
    private LocalizacaoDTO localizacao;
    private String rotulopersonalizado;
    private List<ImagemAnuncioDTO> imagens = new ArrayList<>();
    private List<AnunciotagDTO> anuncioTags = new ArrayList<>();

    public Anuncio toEntity(){
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(this.titulo);
        anuncio.setDescricao(this.descricao);
        anuncio.setPreco(this.preco);
        anuncio.setEstado(EstadoEnum.ATIVO);
        anuncio.setTipoanuncio(this.tipoanuncio != null ? this.tipoanuncio.toIdEntity() : null);
        anuncio.setUtilizador(this.utilizador != null ? this.utilizador.toIdEntity() : null);
        anuncio.setCategoria(this.categoria != null ? this.categoria.toIdEntity() : null);
        anuncio.setSubcategoria(this.subcategoria != null ? this.subcategoria.toIdEntity() : null);
        anuncio.setTipoProduto(this.tipoproduto != null ? this.tipoproduto.toIdEntity() : null);
        anuncio.setStock(this.stock);
        anuncio.setUnidadesMedida(this.unidadesMedida != null ? this.unidadesMedida.toIdEntity() : null);
        anuncio.setLocalizacao(this.localizacao != null ? this.localizacao.toEntity() : null);

        anuncio.setRotulopersonalizado(this.rotulopersonalizado);
        anuncio.setImagens(this.imagens.stream().map(ImagemAnuncioDTO::toEntity)
                .peek(t->t.setAnuncio(anuncio)).toList());
        anuncio.setAnuncioTags(this.anuncioTags.stream()
                .map(AnunciotagDTO::toIdEntity)
                .peek(t->t.setAnuncio(anuncio))
                .toList());
        return anuncio;
    }
}
