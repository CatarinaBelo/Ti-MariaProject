package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.ImagemAnuncio;
import com.example.timariaproject.enums.EstadoEnum;
import com.example.timariaproject.repository.AnuncioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnuncioService {
    private final AnuncioRepository anuncioRepository;

    // depois fazer só enviar as com estado ativo
    public List<AnuncioDTO> getAllAnuncios() {
        return anuncioRepository.findAll()
                .stream()
                .map(anuncio -> new AnuncioDTO(anuncio.getTitulo(), anuncio.getDescricao(), anuncio.getPreco(),
                        anuncio.getDatacriacao(), anuncio.getDataatualizacao(),
                        anuncio.getTipo(), anuncio.getUtilizador(), anuncio.getCategoria(), anuncio.getSubcategoria(),
                        anuncio.getTipoProduto(), anuncio.getStock(), anuncio.getUnidadesMedida(), anuncio.getLocalizacao(), anuncio.getImagens()))
                .toList();
    }

    public String salvarAnuncio(AnuncioDTO anuncioDTO) {
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioDTO.getTitulo());
        anuncio.setDescricao(anuncioDTO.getDescricao());
        anuncio.setPreco(anuncioDTO.getPreco());
        anuncio.setDatacriacao(anuncioDTO.getDatacriacao());
        anuncio.setDataatualizacao(anuncioDTO.getDataatualizacao());
        anuncio.setTipo(anuncioDTO.getTipo());
        anuncio.setUtilizador(anuncioDTO.getUtilizador());
        anuncio.setCategoria(anuncioDTO.getCategoria());
        anuncio.setSubcategoria(anuncioDTO.getSubcategoria());
        anuncio.setTipoProduto(anuncioDTO.getTipoProduto());
        anuncio.setStock(anuncioDTO.getStock());
        anuncio.setUnidadesMedida(anuncioDTO.getUnidadesMedida());
        anuncio.setLocalizacao(anuncioDTO.getLocalizacao());

        anuncio.setEstado(EstadoEnum.ATIVO);

        // Criar lista de imagens e associá-las ao anúncio
        List<ImagemAnuncio> imagens = anuncioDTO.getImagens().stream().map(imagemAnuncio -> {
            ImagemAnuncio img = new ImagemAnuncio();
            img.setImagem(imagemAnuncio.getImagem());
            img.setAnuncio(anuncio);
            return img;
        }).collect(Collectors.toList());

        anuncio.setImagens(imagens);
        anuncioRepository.save(anuncio);

        return "Anuncio Saved";
    }

}
