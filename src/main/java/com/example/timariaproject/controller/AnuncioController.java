package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/anuncio")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<AnuncioDTO>> getAnuncioDetails() {
        return ResponseEntity.ok(anuncioService.getAllAnuncios());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> criarAnuncio(@RequestBody AnuncioDTO anuncioDTO) {
        anuncioService.salvarAnuncio(anuncioDTO);
        return ResponseEntity.ok().body("Save Anuncio Succeeded");
    }
}
