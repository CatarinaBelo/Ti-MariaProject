package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.AnunciotagDTO;
import com.example.timariaproject.DTOs.TagDTO;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;


@Entity
@Table(name = "tag")
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nometag;

    public TagDTO toDto() {
        return TagDTO.builder()
                .nometag(this.nometag)
                .build();
    }
}
