package com.example.timariaproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private String nomeproduto;
    private String tipoproduto;
    private Integer stock;
    private Integer anuncio;
    private Integer categoriaproduto;
    private String tags;
    private Integer idsubcategoria;

}
