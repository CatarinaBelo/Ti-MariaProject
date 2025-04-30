package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Favoritos;
import com.example.timariaproject.enums.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FavoritosDTO {
    private Integer id;
    private UserDTO userDTO;
    private AnuncioDTO anuncioDTO;
    private LocalDateTime datacriacao;

    /*public Favoritos toEntity(){
        Favoritos favoritos = new Favoritos();
        favoritos.setId(id);
        favoritos.setUtilizador(this.userDTO != null ? this.userDTO.toIdEntity() : null);
        favoritos.setAnuncio(this.anuncioDTO != null ? this.anuncioDTO.to() : null);

        return favoritos;
    }*/
}
