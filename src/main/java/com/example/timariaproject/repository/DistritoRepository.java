package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Distrito;
import com.example.timariaproject.domain.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    List<Distrito> findAll();
}

