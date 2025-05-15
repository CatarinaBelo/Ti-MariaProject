package com.example.timariaproject.repository;

import com.example.timariaproject.domain.LikePostagem;
import com.example.timariaproject.domain.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikePostagemRepository extends JpaRepository<LikePostagem, Long> {



    List<LikePostagem> findByPostagemId(Long postagemId);

    boolean existsByPostagemIdAndUtilizador(Long postagemId, Utilizador utilizador);

}
