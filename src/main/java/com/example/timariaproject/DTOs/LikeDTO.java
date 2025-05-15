package com.example.timariaproject.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    private Long id;

    private Long postagemId;

    private Long utilizadorId; // ← opcional se extraído da autenticação
}
