package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.LocalizacaoDTO;
import com.example.timariaproject.DTOs.TipoprodutoDTO;
import com.example.timariaproject.service.LocalizacaoService;
import com.example.timariaproject.service.TipoprodutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/localizacao")
public class LocalizacaoController {
    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<LocalizacaoDTO>> listarLocalizacoes() {
        List<LocalizacaoDTO> localizacoes = localizacaoService.getAllLocalizacao();
        return ResponseEntity.ok(localizacoes);
    }
}
