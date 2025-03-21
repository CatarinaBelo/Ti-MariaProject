package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.*;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.domain.Distrito;
import com.example.timariaproject.domain.Subcategoria;
import com.example.timariaproject.enums.EstadoEnum;
import com.example.timariaproject.repository.*;
import com.example.timariaproject.specifications.AnuncioSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnuncioService {
    private final AnuncioRepository anuncioRepository;
    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;
    private final DistritoRepository distritoRepository;
    private final ConcelhoRepository concelhoRepository;
    private final FreguesiaRepository freguesiaRepository;

    public Page<AnuncioDTO> getAllAnuncios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("datacriacao").descending());
        Page<Anuncio> anunciosPage = anuncioRepository.findAllByEstado(EstadoEnum.ATIVO, pageable);

        return anunciosPage.map(Anuncio::toDto);
    }

    public String salvarAnuncio(AnuncioSaveDTO anuncioDTO) {
        anuncioRepository.save(anuncioDTO.toEntity());
        return "Anuncio Saved";
    }

    /*public List<AnuncioDTO> listarAnunciosPorCategoria(Integer idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return anuncioRepository
                .findByCategoria(categoria)
                .stream()
                .map(Anuncio::toDto)
                .toList();
    }

    public List<AnuncioDTO> listarAnunciosPorSubcategoria(Integer idSubcategoria) {
        Subcategoria subcategoria = subcategoriaRepository.findById(idSubcategoria)
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada"));

        return anuncioRepository
                .findBySubcategoria(subcategoria)
                .stream()
                .map(Anuncio::toDto)
                .toList();
    }

    public List<AnuncioDTO> listarAnunciosPorDistrito(Integer idDistrito) {
        if (!distritoRepository.existsById(idDistrito)) {
            throw new RuntimeException("Distrito com ID " + idDistrito + " não encontrado.");
        }
        return anuncioRepository.findByLocalizacao_DistritoId(idDistrito)
                .stream()
                .map(Anuncio::toDto)
                .toList();
    }

    public List<AnuncioDTO> listarAnunciosPorConcelho(Integer idConcelho) {
        if (!concelhoRepository.existsById(idConcelho)) {
            throw new RuntimeException("Concelho com ID " + idConcelho + " não encontrado.");
        }
        return anuncioRepository.findByLocalizacao_ConcelhoId(idConcelho)
                .stream()
                .map(Anuncio::toDto)
                .toList();
    }

    public List<AnuncioDTO> listarAnunciosPorFreguesia(Integer idFreguesia) {
        if (!freguesiaRepository.existsById(idFreguesia)) {
            throw new RuntimeException("Freguesia com ID " + idFreguesia + " não encontrada.");
        }
        return anuncioRepository.findByLocalizacao_FreguesiaId(idFreguesia)
                .stream()
                .map(Anuncio::toDto)
                .toList();
    }*/

    public Page<AnuncioDTO> searchAnunciosByFilters(
            Integer categoriaId,
            Integer subcategoriaId,
            Integer distritoId,
            Integer concelhoId,
            Integer freguesiaId,
            int page,
            int size
    ) {
        Specification<Anuncio> spec = Specification
                .where(AnuncioSpecification.hasCategoriaId(categoriaId))
                .and(AnuncioSpecification.hasSubcategoriaId(subcategoriaId))
                .and(AnuncioSpecification.hasDistritoId(distritoId))
                .and(AnuncioSpecification.hasConcelhoId(concelhoId))
                .and(AnuncioSpecification.hasFreguesiaId(freguesiaId));

        Pageable pageable = PageRequest.of(page, size, Sort.by("datacriacao").descending());

        return anuncioRepository.findAll(spec, pageable)
                .map(Anuncio::toDto);
    }


    public Page<AnuncioDTO> searchbarAnuncios(String tipoProdutoNome, String rotulo, int page, int size) {
        Specification<Anuncio> spec = Specification.where(AnuncioSpecification.hasTipoProduto(tipoProdutoNome))
                .and(AnuncioSpecification.hasRotuloPersonalizado(rotulo));

        Pageable pageable = PageRequest.of(page, size, Sort.by("datacriacao").descending());

        Page<Anuncio> anunciosPage = anuncioRepository.findAll(spec, pageable);

        return anunciosPage.map(Anuncio::toDto);
    }


}
