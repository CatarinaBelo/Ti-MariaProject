package com.example.timariaproject.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostagemDTO {
    private Long id;
    private String conteudo;

    private Long utilizadorId;  // ← ID do utilizador (opcional se autenticado)
    private String nomeAutor;   // ← nome visível do autor
}
