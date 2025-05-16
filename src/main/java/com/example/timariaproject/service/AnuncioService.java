package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.*;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Anunciotag;
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
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.Predicate;


import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

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

    /*public Page<AnuncioDTO> buscarPorRaio(double latitude, double longitude, double raioKm, int page, int size) {
        Specification<Anuncio> spec = AnuncioSpecification.withinRaioKm(latitude, longitude, raioKm);
        Pageable pageable = PageRequest.of(page, size, Sort.by("datacriacao").descending());
        Page<Anuncio> anuncioPage = anuncioRepository.findAll(spec, pageable);
        return anuncioPage.map(Anuncio::toDto);
    }*/

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
            List<Integer> categoriaIds,
            List<Integer> subcategoriaIds,
            List<Integer> distritoIds,
            List<Integer> tagIds,
            List<Integer> tipoanuncioIds,
            int page,
            int size
    ) {
        Specification<Anuncio> spec = Specification
                .where(AnuncioSpecification.hasCategoriaIds(categoriaIds))
                .and(AnuncioSpecification.hasSubcategoriaIds(subcategoriaIds))
                .and(AnuncioSpecification.hasDistritoIds(distritoIds))
                .and(AnuncioSpecification.hasTagIds(tagIds))
                .and(AnuncioSpecification.hastipoanuncioIds(tipoanuncioIds));

        Pageable pageable = PageRequest.of(page, size, Sort.by("datacriacao").descending());
        return anuncioRepository.findAll(spec, pageable).map(Anuncio::toDto);
    }


    public Page<AnuncioDTO> searchbarAnuncios(String tipoProdutoNome, String rotulo, int page, int size) {
        Specification<Anuncio> spec = (root, query, cb) -> {
            Predicate predicateTipo = AnuncioSpecification.hasTipoProduto(tipoProdutoNome).toPredicate(root, query, cb);
            Predicate predicateRotulo = AnuncioSpecification.hasRotuloPersonalizado(rotulo).toPredicate(root, query, cb);

            if (predicateTipo != null && predicateRotulo != null) {
                return cb.or(predicateTipo, predicateRotulo); // OR entre os dois
            } else if (predicateTipo != null) {
                return predicateTipo;
            } else {
                return predicateRotulo;
            }
        };

        Pageable pageable = PageRequest.of(page, size, Sort.by("datacriacao").descending());
        Page<Anuncio> anunciosPage = anuncioRepository.findAll(spec, pageable);
        return anunciosPage.map(Anuncio::toDto);
    }

    public void deleteAnuncioById(Integer id) {
        if (!anuncioRepository.existsById(id)) {
            throw new RuntimeException("Anúncio not found with id: " + id);
        }
        anuncioRepository.deleteById(id);
    }

    @Transactional
    public void processVenda(Integer anuncioId, int quantidade) {
        Anuncio anuncio = anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anúncio not found"));

        if (!anuncio.getTipoanuncio().getTipo().equalsIgnoreCase("venda")) {
            throw new RuntimeException("Anúncio is not of tipo 'venda'");
        }

        if (anuncio.getStock() == null || anuncio.getStock() < quantidade) {
            throw new RuntimeException("Insufficient stock");
        }

        int novoStock = anuncio.getStock() - quantidade;
        anuncio.setStock(novoStock);

        if (novoStock > 0) {
            anuncioRepository.save(anuncio);
        } else {
            anuncio.setEstado(EstadoEnum.NAO_ATIVO);
        }
    }


    public AnuncioDTO getAnuncioDetails(Integer anuncioId) {
        Anuncio anuncio = anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado com ID: " + anuncioId));
        return anuncio.toDto();
    }

    public List<AnuncioDTO> getAnunciosByUser(Integer idUtilizador) {
        List<Anuncio> anuncios = anuncioRepository.findByUtilizadorId(idUtilizador);
        return anuncios.stream()
                .map(Anuncio::toDto)
                .collect(Collectors.toList());
    }


    public void updateAnuncio(Integer anuncioId, AnuncioEditDTO dto) {
        Anuncio anuncio = anuncioRepository.findById(
                anuncioId).orElseThrow(() -> new RuntimeException("Anuncio not found"));

        if (dto.getTitulo() != null) {
            anuncio.setTitulo(dto.getTitulo());
        }
        if (dto.getDescricao() != null) {
            anuncio.setDescricao(dto.getDescricao());
        }
        if (dto.getPreco() != null) {
            anuncio.setPreco(dto.getPreco());
        }
        if (dto.getTipoanuncio() != null) {
            anuncio.setTipoanuncio(dto.getTipoanuncio().toIdEntity());
        }
        if (dto.getCategoria() != null) {
            anuncio.setCategoria(dto.getCategoria().toIdEntity());
        }
        if (dto.getSubcategoria() != null) {
            anuncio.setSubcategoria(dto.getSubcategoria().toIdEntity());
        }
        if (dto.getTipoproduto() != null) {
            anuncio.setTipoProduto(dto.getTipoproduto().toIdEntity());
        }
        if (dto.getStock() != null) {
            anuncio.setStock(dto.getStock());
        }
        if (dto.getUnidadesMedida() != null) {
            anuncio.setUnidadesMedida(dto.getUnidadesMedida().toIdEntity());
        }
        if (dto.getLocalizacao() != null) {
            anuncio.setLocalizacao(dto.getLocalizacao().toIdEntity());
        }
        if (dto.getRotulopersonalizado() != null) {
            anuncio.setRotulopersonalizado(dto.getRotulopersonalizado());
        }

        // Only update imagens if present
        if (dto.getImagens() != null) {
            anuncio.getImagens().clear();
            anuncio.getImagens().addAll(dto.getImagens().stream()
                    .map(imagemDTO -> {
                        var imagem = imagemDTO.toEntity();
                        imagem.setAnuncio(anuncio);
                        return imagem;
                    })
                    .toList());
        }

        // Only update tags if present
        if (dto.getAnuncioTags() != null) {
            Set<Integer> existingTagIds = anuncio.getAnuncioTags().stream()
                    .map(anunciotag -> anunciotag.getTag().getId())
                    .collect(Collectors.toSet());

            dto.getAnuncioTags().stream()
                    .filter(tagDTO -> tagDTO.getTag() != null)
                    .filter(tagDTO -> !existingTagIds.contains(tagDTO.getTag().getId())) // only new tags
                    .map(tagDTO -> {
                        var tag = tagDTO.toIdEntity();
                        tag.setAnuncio(anuncio);
                        return tag;
                    })
                    .forEach(anuncio.getAnuncioTags()::add);
        }

        anuncioRepository.save(anuncio);
    }

    public void updateStock(Integer id, int novoStock) {
        if (novoStock < 0) {
            throw new IllegalArgumentException("O stock não pode ser negativo.");
        }

        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Anúncio não encontrado"));

        anuncio.setStock(novoStock);
        anuncioRepository.save(anuncio);
    }

    public void aplicarDesconto(Integer id, double valorpercentagem) {
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado com ID: " + id));

        double precoOriginal = anuncio.getPreco();
        double descontoDecimal = valorpercentagem / 100.0; // Converter para 0.2 por exemplo
        double novoPreco = precoOriginal * (1 - descontoDecimal);

        anuncio.setPreco(novoPreco);
        anuncioRepository.save(anuncio);
    }

    public List<AnuncioDTO> findByTipoAnuncioId(Integer idTipoanuncio) {
        List<Anuncio> anuncios = anuncioRepository.findByTipoanuncioId(idTipoanuncio);
        return anuncios.stream()
                .map(Anuncio::toDto)
                .collect(Collectors.toList());
    }





}
