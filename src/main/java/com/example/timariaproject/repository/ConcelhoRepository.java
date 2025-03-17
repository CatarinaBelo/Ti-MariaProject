package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Concelho;
import com.example.timariaproject.domain.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcelhoRepository extends JpaRepository<Concelho, Integer> {
    List<Concelho> findAll();
}

