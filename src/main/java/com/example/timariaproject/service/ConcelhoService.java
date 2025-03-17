package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.ConcelhoDTO;
import com.example.timariaproject.DTOs.DistritoDTO;
import com.example.timariaproject.domain.Concelho;
import com.example.timariaproject.domain.Distrito;
import com.example.timariaproject.repository.ConcelhoRepository;
import com.example.timariaproject.repository.DistritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcelhoService {
    private final ConcelhoRepository concelhoRepository;

    public List<ConcelhoDTO> getAllConcelhos() {
        return concelhoRepository.findAll()
                .stream()
                .map(Concelho::toDto)
                .toList();
    }
}
