package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {

}
