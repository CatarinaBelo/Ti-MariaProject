package com.example.timariaproject.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComentarioDTO {
    private Long id;
    private String texto;
    private String autor;      // Nome ou e-mail do autor, para visualização
    private Long userId;       // ← NECESSÁRIO para criação do comentário
    private Long postagemId;
}
