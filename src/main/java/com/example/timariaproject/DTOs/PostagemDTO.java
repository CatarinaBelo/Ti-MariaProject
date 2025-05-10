package com.example.timariaproject.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostagemDTO {
    private Long id;
    private String conteudo;
    private String autor;
}
