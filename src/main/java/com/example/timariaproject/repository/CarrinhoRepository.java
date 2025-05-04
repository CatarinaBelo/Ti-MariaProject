package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Carrinho;
import com.example.timariaproject.domain.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
    List<Carrinho> findByUtilizador(Utilizador utilizador);
    Optional<Carrinho> findByUtilizadorAndAnuncio(Utilizador utilizador, Anuncio anuncio);
}