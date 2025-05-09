package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.CarrinhoBatchDTO;
import com.example.timariaproject.DTOs.CarrinhoDTO;
import com.example.timariaproject.DTOs.CarrinhoItemDTO;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Carrinho;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.AnuncioRepository;
import com.example.timariaproject.repository.CarrinhoRepository;
import com.example.timariaproject.repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final UtilizadorRepository utilizadorRepository;
    private final AnuncioRepository anuncioRepository;

    public List<String> addItensToCarrinho(CarrinhoBatchDTO batchDTO) {
        List<String> mensagens = new ArrayList<>();
        Integer idUtilizador = batchDTO.getIdUtilizador();

        Utilizador utilizador = utilizadorRepository.findById(idUtilizador)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        for (CarrinhoItemDTO item : batchDTO.getItens()) {
            Integer idAnuncio = item.getIdAnuncio();
            int quantidade = item.getQuantidade();

            if (quantidade <= 0) {
                mensagens.add("Quantidade inválida para anúncio " + idAnuncio);
                continue;
            }

            Anuncio anuncio = anuncioRepository.findById(idAnuncio)
                    .orElse(null);

            if (anuncio == null) {
                mensagens.add("Anúncio " + idAnuncio + " não encontrado.");
                continue;
            }

            Carrinho carrinho = carrinhoRepository.findByUtilizadorAndAnuncio(utilizador, anuncio).orElse(null);
            int quantidadeAtual = carrinho != null ? carrinho.getQuantidade() : 0;
            int novaQuantidade = quantidadeAtual + quantidade;

            if (novaQuantidade > anuncio.getStock()) {
                mensagens.add("Stock insuficiente para anúncio " + idAnuncio + ". Stock atual: " + anuncio.getStock());
                continue;
            }

            if (carrinho == null) {
                carrinho = new Carrinho();
                carrinho.setUtilizador(utilizador);
                carrinho.setAnuncio(anuncio);
            }

            carrinho.setQuantidade(novaQuantidade);
            carrinhoRepository.save(carrinho);
            mensagens.add("Anúncio " + idAnuncio + " adicionado com sucesso.");
        }

        return mensagens;
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
