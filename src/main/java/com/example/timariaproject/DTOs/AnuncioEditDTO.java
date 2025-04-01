package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.enums.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnuncioEditDTO {
    private String titulo;
    private String descricao;
    private Double preco;
    private TipoanuncioDTO tipoanuncio;
    private CategoriaDTO categoria;
    private SubcategoriaDTO subcategoria;
    private TipoprodutoDTO tipoproduto;
    private Integer stock;
    private UnidadesmedidaDTO unidadesMedida;
    private LocalizacaoDTO localizacao;
    private String rotulopersonalizado;
    private List<ImagemAnuncioDTO> imagens;
    private List<AnunciotagDTO> anuncioTags;

}
