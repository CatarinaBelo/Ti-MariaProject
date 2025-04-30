package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.FavoritosDTO;
import com.example.timariaproject.service.FavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/favoritos")
public class FavoritosController {

    @Autowired
    private FavoritosService favoritosService;

    /*
      Adiciona um anúncio aos favoritos do utilizador.
     * Endpoint: POST /favoritos/addFavorito/{idUtilizador}/{idAnuncio}
     */
    @PostMapping("/addFavorito/{idUtilizador}/{idAnuncio}")
    public ResponseEntity<Void> adicionarFavorito(
            @PathVariable Integer idUtilizador,
            @PathVariable Integer idAnuncio
    ) {
        favoritosService.adicionarFavorito(idUtilizador, idAnuncio);
        return ResponseEntity.ok().build();
    }

    /*
      Remove um anúncio dos favoritos do utilizador.
     * Endpoint: DELETE /favoritos/deleteFavorito/{idUtilizador}/{idAnuncio}
     */
    @DeleteMapping("/deleteFavorito/{idUtilizador}/{idAnuncio}")
    public ResponseEntity<Void> removerFavorito(
            @PathVariable Integer idUtilizador,
            @PathVariable Integer idAnuncio
    ) {
        favoritosService.removerFavorito(idUtilizador, idAnuncio);
        return ResponseEntity.ok().build();
    }

    /*
      Lista todos os favoritos do utilizador.
     * Endpoint: GET /favoritos/getFavoritos/{idUtilizador}
     */
    @GetMapping("/getFavoritos/{idUtilizador}")
    public ResponseEntity<List<FavoritosDTO>> listarFavoritosDoUtilizador(
            @PathVariable Integer idUtilizador
    ) {
        List<FavoritosDTO> favoritos = favoritosService.listarFavoritosDoUtilizador(idUtilizador);
        return ResponseEntity.ok(favoritos);
    }
}

