package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Favoritos;
import com.example.timariaproject.domain.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoritosRepository extends JpaRepository<Favoritos, Integer> {
    List<Favoritos> findByUtilizador(Utilizador user);
    Optional<Favoritos> findByUtilizadorAndAnuncio(Utilizador user, Anuncio anuncio);
}
