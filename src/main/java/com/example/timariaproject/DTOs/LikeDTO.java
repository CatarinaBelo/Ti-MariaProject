package com.example.timariaproject.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDTO {
    private Long id;
    private Long postagemId;
    private Long userId; // <-- usar o ID do utilizador, não o nome
}
