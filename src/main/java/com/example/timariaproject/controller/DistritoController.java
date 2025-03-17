package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.DistritoDTO;
import com.example.timariaproject.DTOs.LocalizacaoDTO;
import com.example.timariaproject.service.DistritoService;
import com.example.timariaproject.service.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/distrito")
public class DistritoController {
    @Autowired
    private DistritoService distritoService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<DistritoDTO>> listarDistritos() {
        List<DistritoDTO> distritos = distritoService.getAllDistritos();
        return ResponseEntity.ok(distritos);
    }
}
