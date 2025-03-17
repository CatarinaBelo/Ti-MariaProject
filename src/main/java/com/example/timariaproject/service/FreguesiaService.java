package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.ConcelhoDTO;
import com.example.timariaproject.DTOs.FreguesiaDTO;
import com.example.timariaproject.domain.Concelho;
import com.example.timariaproject.domain.Freguesia;
import com.example.timariaproject.repository.ConcelhoRepository;
import com.example.timariaproject.repository.FreguesiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreguesiaService {
    private final FreguesiaRepository freguesiaRepository;

    public List<FreguesiaDTO> getAllFreguesias() {
        return freguesiaRepository.findAll()
                .stream()
                .map(Freguesia::toDto)
                .toList();
    }
}
