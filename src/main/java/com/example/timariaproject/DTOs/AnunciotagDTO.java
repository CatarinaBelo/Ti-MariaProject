package com.example.timariaproject.DTOs;

import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Anunciotag;
import com.example.timariaproject.domain.Freguesia;
import com.example.timariaproject.domain.Tag;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnunciotagDTO {
    private Integer id;
    private AnuncioSaveDTO anuncio;
    private TagDTO tag;

    public Anunciotag toEntity(){
        Anunciotag anunciotag = new Anunciotag();
        anunciotag.setId(this.id);
        //anunciotag.setAnuncio(this.anuncio != null ? this.anuncio.toEntity() : null);
        anunciotag.setTag(this.tag != null ? this.tag.toEntity() : null);
        return anunciotag;
    }

    public Anunciotag toIdEntity(){
        Anunciotag anunciotag = new Anunciotag();
        anunciotag.setId(this.id);
        anunciotag.setTag(this.tag != null ? this.tag.toIdEntity() : null);
        return anunciotag;
    }

}
