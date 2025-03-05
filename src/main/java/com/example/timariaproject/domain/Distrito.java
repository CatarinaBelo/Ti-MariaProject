package com.example.timariaproject.domain;
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
}
