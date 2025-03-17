package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.LocalizacaoDTO;
import com.example.timariaproject.DTOs.TipoprodutoDTO;
import com.example.timariaproject.domain.Localizacao;
import com.example.timariaproject.domain.Tipoproduto;
import com.example.timariaproject.repository.LocalizacaoRepository;
import com.example.timariaproject.repository.TipoprodutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalizacaoService {
    private final LocalizacaoRepository localizacaoRepository;

    public List<LocalizacaoDTO> getAllLocalizacao() {
        return localizacaoRepository.findAll()
                .stream()
                .map(Localizacao::toDto)
                .toList();
    }
}
