package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.CategoriaDTO;
import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(Categoria::toDto)
                .toList();
    }
}
