package com.example.timariaproject.domain;

import com.example.timariaproject.DTOs.ConcelhoDTO;
import com.example.timariaproject.DTOs.FreguesiaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "freguesia")
@Getter
@Setter
public class Freguesia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomefreguesia;

    @ManyToOne
    @JoinColumn(name = "idconcelho", nullable = false)
    private Concelho concelho;

    public FreguesiaDTO toDto() {
        return FreguesiaDTO.builder()
                .nomefreguesia(this.nomefreguesia)
                .concelho(this.concelho != null ? this.concelho.toDto() : null)
                .build();
    }
}
