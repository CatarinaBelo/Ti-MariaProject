package com.example.timariaproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CarrinhoBatchDTO {
    private Integer idUtilizador;
    private List<CarrinhoItemDTO> itens;


}
