package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Concelho;
import com.example.timariaproject.domain.Freguesia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreguesiaRepository extends JpaRepository<Freguesia, Integer> {
    List<Freguesia> findAll();
}

