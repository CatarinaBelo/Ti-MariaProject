package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.ComentarioDTO;
import com.example.timariaproject.domain.Comentario;
import com.example.timariaproject.domain.Postagem;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.ComentarioRepository;
import com.example.timariaproject.repository.PostagemRepository;
import com.example.timariaproject.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public ComentarioDTO criarComentario(ComentarioDTO dto) {
        Postagem postagem = postagemRepository.findById(dto.getPostagemId())
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));

        Utilizador autor = utilizadorRepository.findById(dto.getUserId().intValue())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));


        Comentario comentario = new Comentario();
        comentario.setTexto(dto.getTexto());
        comentario.setAutor(autor);
        comentario.setPostagem(postagem);

        comentario = comentarioRepository.save(comentario);
        return toDTO(comentario);
    }

    public List<ComentarioDTO> listarPorPostagem(Long postagemId) {
        return comentarioRepository.findByPostagemId(postagemId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ComentarioDTO toDTO(Comentario comentario) {
        return ComentarioDTO.builder()
                .id(comentario.getId())
                .texto(comentario.getTexto())
                .autor(comentario.getAutor().getNome()) // ou .getUsername()
                .userId(comentario.getAutor().getId().longValue())
                // <-- Adiciona isto
                .postagemId(comentario.getPostagem().getId())
                .build();
    }

}
