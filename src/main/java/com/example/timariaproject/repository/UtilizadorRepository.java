package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {
    Optional<Utilizador> findByEmail(String email);
}
