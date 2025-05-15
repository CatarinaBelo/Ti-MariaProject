package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.PostagemDTO;
import com.example.timariaproject.domain.Postagem;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.PostagemRepository;
import com.example.timariaproject.repository.UtilizadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public PostagemDTO criarPostagem(PostagemDTO dto, Principal principal) {
        Utilizador utilizador = utilizadorRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        Postagem nova = new Postagem();
        nova.setConteudo(dto.getConteudo());
        nova.setUtilizador(utilizador);
        nova.setAutor(utilizador.getNome()); // opcional: manter o nome visível na altura da criação

        Postagem salva = postagemRepository.save(nova);
        return toDTO(salva);
    }

    public List<PostagemDTO> listarTodas() {
        return postagemRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PostagemDTO buscarPorId(Long id) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada"));
        return toDTO(postagem);
    }

    public PostagemDTO atualizarPostagem(Long id, PostagemDTO dto) {
        Postagem existente = postagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada"));

        existente.setConteudo(dto.getConteudo());
        // O autor não deve ser alterado

        Postagem atualizada = postagemRepository.save(existente);
        return toDTO(atualizada);
    }

    public void apagarPostagem(Long id) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada"));
        postagemRepository.delete(postagem);
    }

    private PostagemDTO toDTO(Postagem postagem) {
        return PostagemDTO.builder()
                .id(postagem.getId())
                .conteudo(postagem.getConteudo())
                .utilizadorId(postagem.getUtilizador().getId().longValue())
                .nomeAutor(postagem.getAutor())
                .build();
    }
}
