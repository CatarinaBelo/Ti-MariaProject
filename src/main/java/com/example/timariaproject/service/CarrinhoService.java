package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.CarrinhoDTO;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Carrinho;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.AnuncioRepository;
import com.example.timariaproject.repository.CarrinhoRepository;
import com.example.timariaproject.repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final UtilizadorRepository utilizadorRepository;
    private final AnuncioRepository anuncioRepository;

    public String  addToCarrinho(Integer idUtilizador, Integer idAnuncio, int quantidade) {
        if (quantidade <= 0) return "A quantidade deve ser maior que zero.";

        Utilizador utilizador = utilizadorRepository.findById(idUtilizador)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        Anuncio anuncio = anuncioRepository.findById(idAnuncio)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        Carrinho carrinho = carrinhoRepository.findByUtilizadorAndAnuncio(utilizador, anuncio).orElse(null);
        int quantidadeAtual = carrinho != null ? carrinho.getQuantidade() : 0;
        int novaQuantidade = quantidadeAtual + quantidade;

        if (novaQuantidade > anuncio.getStock()) {
            return "A quantidade excede o stock disponível. Stock atual: " + anuncio.getStock();
        }

        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setUtilizador(utilizador);
            carrinho.setAnuncio(anuncio);
        }

        carrinho.setQuantidade(novaQuantidade);
        carrinhoRepository.save(carrinho);

        return "Produto adicionado ao carrinho com sucesso.";
    }

    public void removeFromCarrinho(Integer idCarrinho) {
        if (!carrinhoRepository.existsById(idCarrinho)) {
            throw new RuntimeException("Item de carrinho não encontrado.");
        }
        carrinhoRepository.deleteById(idCarrinho);
    }

    public List<CarrinhoDTO> listUsersCarrinho(Integer idUtilizador) {
        Utilizador utilizador = utilizadorRepository.findById(idUtilizador)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        return carrinhoRepository.findByUtilizador(utilizador).stream()
                .map(Carrinho::toDto)
                .toList();
    }
}
