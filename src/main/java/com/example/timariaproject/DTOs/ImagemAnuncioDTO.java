package com.example.timariaproject.DTOs;

import  com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Concelho;
import com.example.timariaproject.domain.ImagemAnuncio;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagemAnuncioDTO {
    private String imagem;
    //private AnuncioDTO anuncio;

    public ImagemAnuncio toEntity(){
        ImagemAnuncio imagemAnuncio = new ImagemAnuncio();
        imagemAnuncio.setImagem(this.imagem);
        return imagemAnuncio;
    }
}
