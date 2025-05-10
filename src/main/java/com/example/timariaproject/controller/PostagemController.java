package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.PostagemDTO;
import com.example.timariaproject.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postagens")
@CrossOrigin(origins = "*")
public class PostagemController {

    @Autowired
    private PostagemService service;

    // Criar nova postagem
    @PostMapping
    public PostagemDTO criar(@RequestBody PostagemDTO dto) {
        return service.criarPostagem(dto);
    }

    // Listar todas as postagens
    @GetMapping
    public List<PostagemDTO> listar() {
        return service.listarTodas();
    }

    // Buscar uma postagem por ID
    @GetMapping("/{id}")
    public PostagemDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Atualizar uma postagem existente
    @PutMapping("/{id}")
    public PostagemDTO atualizar(@PathVariable Long id, @RequestBody PostagemDTO dto) {
        return service.atualizarPostagem(id, dto);
    }

    // Apagar uma postagem
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable Long id) {
        service.apagarPostagem(id);
    }
}
