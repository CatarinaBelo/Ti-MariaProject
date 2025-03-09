package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.*;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.enums.EstadoEnum;
import com.example.timariaproject.repository.AnuncioRepository;
import com.example.timariaproject.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnuncioService {
    private final AnuncioRepository anuncioRepository;
    private final CategoriaRepository categoriaRepository;


    public List<AnuncioDTO> getAllAnuncios() {
        return anuncioRepository.findAllByEstado(EstadoEnum.ATIVO)
                .stream()
                .map(Anuncio::toDto)
                .toList();
    }

    public String salvarAnuncio(AnuncioSaveDTO anuncioDTO) {
        anuncioRepository.save(anuncioDTO.toEntity());

        return "Anuncio Saved";
    }

    public List<Anuncio> listarAnunciosPorCategoria(Integer idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return anuncioRepository.findByCategoria(categoria);
    }

}
