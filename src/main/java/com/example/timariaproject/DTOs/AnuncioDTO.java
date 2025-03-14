package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AnuncioDTO {
    private String titulo;
    private String descricao;
    private Double preco;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private LocalDateTime datacriacao;
    private LocalDateTime dataatualizacao;
    private TipoanuncioDTO tipoanuncio;
    private UserDTO utilizadorDTO;
    private CategoriaDTO categoria;
    private SubcategoriaDTO subcategoria;
    private TipoprodutoDTO tipoProduto;
    private Integer stock;
    private UnidadesmedidaDTO unidadesMedida;
    private LocalizacaoDTO localizacao;
    private String rotuloPersonalizado;
    private List<ImagemAnuncioDTO> imagens;
    private List<AnunciotagDTO> anuncioTags;
}
