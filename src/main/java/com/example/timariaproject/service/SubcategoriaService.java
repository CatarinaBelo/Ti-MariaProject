package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.SubcategoriaDTO;
import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.domain.Subcategoria;
import com.example.timariaproject.repository.CategoriaRepository;
import com.example.timariaproject.repository.SubcategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubcategoriaService {
    private final SubcategoriaRepository subcategoriaRepository;

    public List<SubcategoriaDTO> getAllSubcategorias() {
        return subcategoriaRepository.findAll()
                .stream()
                .map(Subcategoria::toDto)
                .toList();
    }
}
