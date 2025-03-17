package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.SubcategoriaDTO;
import com.example.timariaproject.DTOs.TipoprodutoDTO;
import com.example.timariaproject.domain.Subcategoria;
import com.example.timariaproject.domain.Tipoproduto;
import com.example.timariaproject.repository.SubcategoriaRepository;
import com.example.timariaproject.repository.TipoprodutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoprodutoService {
    private final TipoprodutoRepository tipoprodutoRepository;

    public List<TipoprodutoDTO> getAllTipoprodutos() {
        return tipoprodutoRepository.findAll()
                .stream()
                .map(Tipoproduto::toDto)
                .toList();
    }
}
