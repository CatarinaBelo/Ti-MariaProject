package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByPostagemId(Long postagemId);
}
