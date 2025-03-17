package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Localizacao;
import com.example.timariaproject.domain.Tipoproduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {
    List<Localizacao> findAll();
}

