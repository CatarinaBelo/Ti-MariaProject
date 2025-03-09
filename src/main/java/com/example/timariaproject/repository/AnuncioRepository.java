package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.enums.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
    List<Anuncio> findAllByEstado(EstadoEnum estado);
    List<Anuncio> findByCategoria(Categoria categoria);
}
