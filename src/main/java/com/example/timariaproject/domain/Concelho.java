package com.example.timariaproject.domain;

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

}
