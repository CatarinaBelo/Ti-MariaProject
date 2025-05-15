package com.example.timariaproject.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private Long id;
    private String texto;

    private Long postagemId;

    private Long utilizadorId; // ← opcional, se autenticado no backend
    private String nomeAutor;  // ← nome para exibição
}
