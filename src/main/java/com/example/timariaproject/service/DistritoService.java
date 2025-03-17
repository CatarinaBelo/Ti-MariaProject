package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.DistritoDTO;
import com.example.timariaproject.DTOs.LocalizacaoDTO;
import com.example.timariaproject.domain.Distrito;
import com.example.timariaproject.domain.Localizacao;
import com.example.timariaproject.repository.DistritoRepository;
import com.example.timariaproject.repository.LocalizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistritoService {
    private final DistritoRepository distritoRepository;

    public List<DistritoDTO> getAllDistritos() {
        return distritoRepository.findAll()
                .stream()
                .map(Distrito::toDto)
                .toList();
    }
}
