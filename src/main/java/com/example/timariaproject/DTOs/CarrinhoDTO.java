package com.example.timariaproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarrinhoDTO {
    private Integer id;
    private UserDTO userDTO;
    private AnuncioDTO anuncioDTO;
    private Integer quantidade;
    private LocalDateTime datacriacao;
}
