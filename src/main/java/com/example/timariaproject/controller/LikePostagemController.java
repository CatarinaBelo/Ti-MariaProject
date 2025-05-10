package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.LikeDTO;
import com.example.timariaproject.service.LikePostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "*")
public class LikePostagemController {

    @Autowired
    private LikePostagemService likeService;

    @PostMapping
    public LikeDTO like(@RequestBody LikeDTO dto) {
        return likeService.darLike(dto);
    }

    @GetMapping("/postagem/{postagemId}")
    public List<LikeDTO> listarPorPostagem(@PathVariable Long postagemId) {
        return likeService.listarPorPostagem(postagemId);
    }
}
