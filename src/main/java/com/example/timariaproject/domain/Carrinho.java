package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.CarrinhoDTO;
import com.example.timariaproject.domain.interfaces.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "carrinho")
@Getter
@Setter
public class Carrinho implements IEntity<CarrinhoDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idutilizador", nullable = false)
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "idanuncio", nullable = false)
    private Anuncio anuncio;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "datacriacao")
    private LocalDateTime datacriacao = LocalDateTime.now();

    @Override
    public CarrinhoDTO toDto() {
        return CarrinhoDTO.builder()
                .id(this.id)
                .userDTO(this.utilizador != null ? this.utilizador.toDto() : null)
                .anuncioDTO(this.anuncio != null ? this.anuncio.toDto() : null)
                .quantidade(this.quantidade)
                .datacriacao(this.datacriacao)
                .build();
    }
}
