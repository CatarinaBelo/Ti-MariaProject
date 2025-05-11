package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.domain.Distrito;
import com.example.timariaproject.domain.Subcategoria;
import com.example.timariaproject.enums.EstadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>, JpaSpecificationExecutor<Anuncio> {
    Page<Anuncio> findAllByEstado(EstadoEnum estado, Pageable pageable);
    List<Anuncio> findByCategoria(Categoria categoria);
    List<Anuncio> findBySubcategoria(Subcategoria subcategoria);
    List<Anuncio> findByLocalizacao_DistritoId(Integer idDistrito);
    List<Anuncio> findByLocalizacao_ConcelhoId(Integer idConcelho);
    List<Anuncio> findByLocalizacao_FreguesiaId(Integer idFreguesia);
    List<Anuncio> findByUtilizadorId(Integer idUtilizador);
    List<Anuncio> findByTipoanuncioId(Integer idTipoanuncio);
}
