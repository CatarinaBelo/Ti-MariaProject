package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.ConcelhoDTO;
import com.example.timariaproject.DTOs.TagDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "concelho")
@Getter
@Setter
public class Concelho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeconcelho;

    @ManyToOne
    @JoinColumn(name = "iddistrito", nullable = false)
    private Distrito distrito;

    public ConcelhoDTO toDto() {
        return ConcelhoDTO.builder()
                .nomeconcelho(this.nomeconcelho)
                .distrito(this.distrito != null ? this.distrito.toDto() : null)
                .build();
    }
}
