package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.PostagemDTO;
import com.example.timariaproject.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/postagens")
@CrossOrigin(origins = "*")
public class PostagemController {

    @Autowired
    private PostagemService service;

    @PostMapping
    public PostagemDTO criar(@RequestBody PostagemDTO dto, Principal principal) {
        return service.criarPostagem(dto, principal);
    }

    @GetMapping
    public List<PostagemDTO> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public PostagemDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PostagemDTO atualizar(@PathVariable Long id, @RequestBody PostagemDTO dto) {
        return service.atualizarPostagem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void apagar(@PathVariable Long id) {
        service.apagarPostagem(id);
    }
}
