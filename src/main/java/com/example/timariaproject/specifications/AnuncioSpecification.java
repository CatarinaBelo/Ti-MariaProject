package com.example.timariaproject.specifications;

import com.example.timariaproject.domain.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AnuncioSpecification {
    public static Specification<Anuncio> hasCategoriaId(Integer categoriaId) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (categoriaId == null) {
                return null;
            }
            return cb.equal(root.get("categoria").get("id"), categoriaId);
        };
    }

    public static Specification<Anuncio> hasSubcategoriaId(Integer subcategoriaId) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (subcategoriaId == null) {
                return null;
            }
            return cb.equal(root.get("subcategoria").get("id"), subcategoriaId);
        };
    }

    public static Specification<Anuncio> hasDistritoId(Integer distritoId) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (distritoId == null) {
                return null;
            }
            Join<Anuncio, Localizacao> localizacaoJoin = root.join("localizacao");
            Join<Localizacao, Distrito> distritoJoin = localizacaoJoin.join("distrito");
            return cb.equal(distritoJoin.get("id"), distritoId);
        };
    }

    public static Specification<Anuncio> hasConcelhoId(Integer concelhoId) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (concelhoId == null) {
                return null;
            }
            Join<Anuncio, Localizacao> localizacaoJoin = root.join("localizacao");
            Join<Localizacao, Concelho> concelhoJoin = localizacaoJoin.join("concelho");
            return cb.equal(concelhoJoin.get("id"), concelhoId);
        };
    }

    public static Specification<Anuncio> hasFreguesiaId(Integer freguesiaId) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (freguesiaId == null) {
                return null;
            }
            Join<Anuncio, Localizacao> localizacaoJoin = root.join("localizacao");
            Join<Localizacao, Freguesia> freguesiaJoin = localizacaoJoin.join("freguesia");
            return cb.equal(freguesiaJoin.get("id"), freguesiaId);
        };
    }

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

    public static Specification<Anuncio> hasTagId(Integer tagId) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (tagId == null) {
                return null;
            }
            Join<Anuncio, Anunciotag> joinTag = root.join("anuncioTags");
            return cb.equal(joinTag.get("tag").get("id"), tagId);
        };
    }


}
