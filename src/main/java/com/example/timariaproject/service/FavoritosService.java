package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.FavoritosDTO;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Favoritos;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.AnuncioRepository;
import com.example.timariaproject.repository.FavoritosRepository;
import com.example.timariaproject.repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritosService {

    private final FavoritosRepository favoritosRepository;
    private final UtilizadorRepository utilizadorRepository;
    private final AnuncioRepository anuncioRepository;

    /*
      Adiciona um anúncio aos favoritos do utilizador.
     */
    public void adicionarFavorito(Integer idUtilizador, Integer idAnuncio) {
        Utilizador utilizador = utilizadorRepository.findById(idUtilizador)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        Anuncio anuncio = anuncioRepository.findById(idAnuncio)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        var existente = favoritosRepository.findByUtilizadorAndAnuncio(utilizador, anuncio);
        if (existente.isPresent()) {
            throw new RuntimeException("Este anúncio já está nos favoritos do utilizador.");
        }

        Favoritos favorito = new Favoritos();
        favorito.setUtilizador(utilizador);
        favorito.setAnuncio(anuncio);
        favoritosRepository.save(favorito);
    }

    /*
      Remove um favorito do utilizador.
     */
    public void removerFavorito(Integer idUtilizador, Integer idAnuncio) {
        Utilizador utilizador = utilizadorRepository.findById(idUtilizador)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        Anuncio anuncio = anuncioRepository.findById(idAnuncio)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        Favoritos favorito = favoritosRepository.findByUtilizadorAndAnuncio(utilizador, anuncio)
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado"));
        favoritosRepository.delete(favorito);
    }

    /*
      Lista todos os favoritos de um utilizador.
     */
    public List<FavoritosDTO> listarFavoritosDoUtilizador(Integer idUtilizador) {
        Utilizador utilizador = utilizadorRepository.findById(idUtilizador)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        return favoritosRepository.findByUtilizador(utilizador)
                .stream()
                .map(Favoritos::toDto)
                .toList();
    }
}
