package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnuncioDTO {
    private String titulo;
    private String descricao;
    private Double preco;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private LocalDateTime datacriacao;
    private LocalDateTime dataatualizacao;
    private String tipo;
    private Utilizador utilizador;
    private Categoria categoria;
    private Subcategoria subcategoria;
    private Tipoproduto tipoProduto;
    private Integer stock;
    private Unidadesmedida unidadesMedida;
    private Localizacao localizacao;
    private List<ImagemAnuncio> imagens;
}
