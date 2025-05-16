package com.example.timariaproject.specifications;

import com.example.timariaproject.domain.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

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

    public static Specification<Anuncio> hasCategoriaIds(List<Integer> categoriaIds) {
        return (root, query, cb) -> {
            if (categoriaIds == null || categoriaIds.isEmpty()) return null;
            return root.get("categoria").get("id").in(categoriaIds);
        };
    }

    public static Specification<Anuncio> hasSubcategoriaIds(List<Integer> subcategoriaIds) {
        return (root, query, cb) -> {
            if (subcategoriaIds == null || subcategoriaIds.isEmpty()) return null;
            return root.get("subcategoria").get("id").in(subcategoriaIds);
        };
    }

    public static Specification<Anuncio> hasDistritoIds(List<Integer> distritoIds) {
        return (root, query, cb) -> {
            if (distritoIds == null || distritoIds.isEmpty()) return null;
            Join<Anuncio, Localizacao> locJoin = root.join("localizacao");
            return locJoin.get("distrito").get("id").in(distritoIds);
        };
    }

    public static Specification<Anuncio> hasTagIds(List<Integer> tagIds) {
        return (root, query, cb) -> {
            if (tagIds == null || tagIds.isEmpty()) return null;
            Join<Anuncio, Anunciotag> tagJoin = root.join("anuncioTags");
            return tagJoin.get("tag").get("id").in(tagIds);
        };
    }


    public static Specification<Anuncio> hastipoanuncioIds(List<Integer> tipoanuncioIds) {
        return (root, query, cb) -> {
            if (tipoanuncioIds == null || tipoanuncioIds.isEmpty()) return null;
            Join<Anuncio, Tipoanuncio> tipoanuncioJoin = root.join("tipoanuncio");
            return tipoanuncioJoin.get("id").in(tipoanuncioIds);
        };
    }

   /* public static Specification<Anuncio> withinRaioKm(Double latitude, Double longitude, Double raioKm) {
        return (Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (latitude == null || longitude == null || raioKm == null) {
                return null;
            }

            Join<Anuncio, Localizacao> localizacaoJoin = root.join("localizacao");

            // Obter latitude e longitude como Number
            Expression<Number> latMicro = localizacaoJoin.get("latitude");
            Expression<Number> longMicro = localizacaoJoin.get("longitude");

// Dividir para converter de micrograus para graus (mantendo Expression<Number>)
            Expression<Number> latDegrees = cb.quot(latMicro, cb.literal(1000000.0));
            Expression<Number> longDegrees = cb.quot(longMicro, cb.literal(1000000.0));

// Converter para radianos (utiliza SQL function 'radians')
            Expression<Double> latRad = cb.function("radians", Double.class, latDegrees);
            Expression<Double> longRad = cb.function("radians", Double.class, longDegrees);

// Conversão da posição do utilizador
            double latUserRad = Math.toRadians(latitude);
            double longUserRad = Math.toRadians(longitude);

// Calcular distância usando a fórmula do Haversine (ou aproximação esférica)
            Expression<Double> distanciaKm = cb.prod(cb.literal(6371.0),
                    cb.function("acos", Double.class,
                            cb.sum(
                                    cb.prod(
                                            cb.literal(Math.cos(latUserRad)),
                                            cb.prod(
                                                    cb.function("cos", Double.class, latRad),
                                                    cb.function("cos", Double.class,
                                                            cb.diff(longRad, cb.literal(longUserRad)))
                                            )
                                    ),
                                    cb.prod(
                                            cb.literal(Math.sin(latUserRad)),
                                            cb.function("sin", Double.class, latRad)
                                    )
                            )
                    )
            );

// Retornar condição de filtro
            return cb.lessThanOrEqualTo(distanciaKm, raioKm);
        };
    }*/


}
