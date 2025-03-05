package com.example.timariaproject.domain;

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
}
