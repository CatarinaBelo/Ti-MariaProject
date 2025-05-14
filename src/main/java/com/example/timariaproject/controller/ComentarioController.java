package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.ComentarioDTO;
import com.example.timariaproject.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public ComentarioDTO criar(@RequestBody ComentarioDTO dto, Principal principal) {
        return comentarioService.criarComentario(dto, principal);
    }

    @GetMapping("/postagem/{postagemId}")
    public List<ComentarioDTO> listarPorPostagem(@PathVariable Long postagemId) {
        return comentarioService.listarPorPostagem(postagemId);
    }
}
