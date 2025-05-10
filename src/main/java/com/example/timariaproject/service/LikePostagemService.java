package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.LikeDTO;
import com.example.timariaproject.domain.LikePostagem;
import com.example.timariaproject.domain.Postagem;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.LikePostagemRepository;
import com.example.timariaproject.repository.PostagemRepository;
import com.example.timariaproject.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikePostagemService {

    @Autowired
    private LikePostagemRepository likeRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;
    @Autowired
    private LikePostagemRepository likePostagemRepository;

    public LikeDTO darLike(LikeDTO dto) {
        Utilizador utilizador = utilizadorRepository.findById(dto.getUserId().intValue())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));


        if (likePostagemRepository.existsByPostagemIdAndUtilizador(dto.getPostagemId(), utilizador)) {
            throw new RuntimeException("Já deu like nesta postagem");
        }

        Postagem postagem = postagemRepository.findById(dto.getPostagemId())
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));

        LikePostagem like = new LikePostagem();
        like.setPostagem(postagem);
        like.setUtilizador(utilizador);

        like = likeRepository.save(like);
        return toDTO(like);
    }

    public List<LikeDTO> listarPorPostagem(Long postagemId) {
        return likeRepository.findByPostagemId(postagemId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private LikeDTO toDTO(LikePostagem like) {
        return LikeDTO.builder()
                .id(like.getId())
                .postagemId(like.getPostagem().getId())
                .userId(Long.valueOf(like.getUtilizador().getId()))

                .build();
    }
}
