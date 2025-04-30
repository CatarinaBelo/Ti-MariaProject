package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.DTOs.FavoritosDTO;
import com.example.timariaproject.domain.interfaces.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "favoritos")
@Getter
@Setter
public class Favoritos implements IEntity<FavoritosDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idutilizador")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "idanuncio")
    private Anuncio anuncio;

    @Column(name = "datacriacao")
    private LocalDateTime datacriacao = LocalDateTime.now();

    @Override
    public FavoritosDTO toDto() {
        return FavoritosDTO.builder()
                .id(this.id)
                .userDTO(this.utilizador != null ? this.utilizador.toDto() : null)
                .anuncioDTO(this.anuncio != null ? this.anuncio.toDto() : null)
                .datacriacao(this.datacriacao)
                .build();
    }
}
