package com.example.timariaproject.repository;

import com.example.timariaproject.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findAll();
}
