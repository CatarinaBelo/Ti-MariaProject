package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
}
