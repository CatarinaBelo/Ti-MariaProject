package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Subcategoria;
import com.example.timariaproject.domain.Tipoproduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipoprodutoRepository extends JpaRepository<Tipoproduto, Integer> {
    List<Tipoproduto> findAll();
}

