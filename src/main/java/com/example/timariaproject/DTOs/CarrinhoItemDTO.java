package com.example.timariaproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarrinhoItemDTO {
    private Integer idAnuncio;
    private int quantidade;
}
