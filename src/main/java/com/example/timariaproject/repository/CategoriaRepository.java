package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    List<Categoria> findAll();
}
