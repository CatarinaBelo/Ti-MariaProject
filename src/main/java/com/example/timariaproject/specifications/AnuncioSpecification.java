package com.example.timariaproject.specifications;

import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Tipoproduto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AnuncioSpecification {
    public static Specification<Anuncio> hasTipoProduto(String tipoProdutoNome) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (tipoProdutoNome == null || tipoProdutoNome.isEmpty()) {
                return null;
            }
            Join<Anuncio, Tipoproduto> tipoProdutoJoin = root.join("tipoProduto");
            return cb.like(cb.lower(tipoProdutoJoin.get("nometipoproduto")), "%"
                    + tipoProdutoNome.toLowerCase() + "%");
        };
    }

    public static Specification<Anuncio> hasRotuloPersonalizado(String rotulo) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (rotulo == null || rotulo.isEmpty()) {
                return null;
            }
            return cb.like(cb.lower(root.get("rotulopersonalizado")), "%" + rotulo.toLowerCase() + "%");
        };
    }
}
