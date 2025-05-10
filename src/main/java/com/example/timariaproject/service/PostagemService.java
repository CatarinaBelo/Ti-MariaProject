package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.PostagemDTO;
import com.example.timariaproject.domain.Postagem;
import com.example.timariaproject.repository.PostagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    // Criar nova postagem
    public PostagemDTO criarPostagem(PostagemDTO dto) {
        Postagem nova = new Postagem();
        nova.setConteudo(dto.getConteudo());
        nova.setAutor(dto.getAutor());
        Postagem salva = postagemRepository.save(nova);
        return toDTO(salva);
    }

    // Listar todas as postagens
    public List<PostagemDTO> listarTodas() {
        return postagemRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public PostagemDTO buscarPorId(Long id) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada"));
        return toDTO(postagem);
    }

    // Atualizar postagem
    public PostagemDTO atualizarPostagem(Long id, PostagemDTO dto) {
        Postagem existente = postagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada"));

        existente.setConteudo(dto.getConteudo());
        existente.setAutor(dto.getAutor());

        Postagem atualizada = postagemRepository.save(existente);
        return toDTO(atualizada);
    }

    // Apagar postagem
    public void apagarPostagem(Long id) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada"));
        postagemRepository.delete(postagem);
    }

    // Conversão para DTO
    private PostagemDTO toDTO(Postagem postagem) {
        PostagemDTO dto = new PostagemDTO();
        dto.setId(postagem.getId());
        dto.setConteudo(postagem.getConteudo());
        dto.setAutor(postagem.getAutor());
        return dto;
    }
}
