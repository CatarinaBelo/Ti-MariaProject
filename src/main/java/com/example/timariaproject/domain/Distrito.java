package com.example.timariaproject.domain;
import com.example.timariaproject.DTOs.DistritoDTO;
import com.example.timariaproject.DTOs.TagDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "distrito")
@Getter
@Setter
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomedistrito;

    public DistritoDTO toDto() {
        return DistritoDTO.builder()
                .nomedistrito(this.nomedistrito)
                .build();
    }
}
