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

import java.security.Principal;
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

    public ComentarioDTO criarComentario(ComentarioDTO dto, Principal principal) {
        Utilizador utilizador = utilizadorRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        Postagem postagem = postagemRepository.findById(dto.getPostagemId())
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));

        Comentario comentario = new Comentario();
        comentario.setTexto(dto.getTexto());
        comentario.setPostagem(postagem);
        comentario.setUtilizador(utilizador);

        Comentario salvo = comentarioRepository.save(comentario);

        return toDTO(salvo);
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
                .postagemId(comentario.getPostagem().getId())
                .utilizadorId(Long.valueOf(comentario.getUtilizador().getId()))
                .nomeAutor(comentario.getUtilizador().getNome())
                .build();
    }
}
