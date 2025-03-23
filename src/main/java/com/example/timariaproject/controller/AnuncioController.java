package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.DTOs.AnuncioSaveDTO;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/anuncio")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping("/paginated/details")
    public ResponseEntity<Page<AnuncioDTO>> getPaginatedActiveAnuncios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<AnuncioDTO> anuncios = anuncioService.getAllAnuncios(page, size);
        return ResponseEntity.ok(anuncios);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> criarAnuncio(@RequestBody AnuncioSaveDTO anuncioDTO) {

        anuncioService.salvarAnuncio(anuncioDTO);
        return ResponseEntity.ok().body("Save Anuncio Succeeded");
    }
/*
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<AnuncioDTO>> listarAnunciosPorCategoria(@PathVariable Integer idCategoria) {
        List<AnuncioDTO> anuncios = anuncioService.listarAnunciosPorCategoria(idCategoria);
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/subcategoria/{idSubcategoria}")
    public ResponseEntity<List<AnuncioDTO>> listarAnunciosPorSubcategoria(@PathVariable Integer idSubcategoria) {
        List<AnuncioDTO> anuncios = anuncioService.listarAnunciosPorSubcategoria(idSubcategoria);
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/distrito/{idDistrito}")
    public ResponseEntity<List<AnuncioDTO>> listarAnunciosPorDistrito(@PathVariable Integer idDistrito) {
        List<AnuncioDTO> anuncios = anuncioService.listarAnunciosPorDistrito(idDistrito);
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/concelho/{idConcelho}")
    public ResponseEntity<List<AnuncioDTO>> listarAnunciosPorConcelho(@PathVariable Integer idConcelho) {
        List<AnuncioDTO> anuncios = anuncioService.listarAnunciosPorConcelho(idConcelho);
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/freguesia/{idFreguesia}")
    public ResponseEntity<List<AnuncioDTO>> listarAnunciosPorFreguesia(@PathVariable Integer idFreguesia) {
        List<AnuncioDTO> anuncios = anuncioService.listarAnunciosPorFreguesia(idFreguesia);
        return ResponseEntity.ok(anuncios);
    }
    */

    @GetMapping("/search/filter")
    public ResponseEntity<Page<AnuncioDTO>> searchAnuncios(
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) Integer subcategoriaId,
            @RequestParam(required = false) Integer distritoId,
            @RequestParam(required = false) Integer concelhoId,
            @RequestParam(required = false) Integer freguesiaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<AnuncioDTO> result = anuncioService.searchAnunciosByFilters(
                categoriaId, subcategoriaId, distritoId, concelhoId, freguesiaId, page, size
        );
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search/bar")
    public ResponseEntity<Page<AnuncioDTO>> searchAnuncios(
            @RequestParam(required = false) String tipoProdutoNome,
            @RequestParam(required = false) String rotulo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<AnuncioDTO> result = anuncioService.searchbarAnuncios(tipoProdutoNome, rotulo, page, size);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnuncio(@PathVariable Integer id) {
        anuncioService.deleteAnuncioById(id);
        return ResponseEntity.ok("Anúncio deleted successfully.");
    }

    @PostMapping("/{id}/venda")
    public ResponseEntity<String> realizarVenda(
            @PathVariable Integer id,
            @RequestParam int quantidade
    ) {
        anuncioService.processVenda(id, quantidade);
        return ResponseEntity.ok("Venda realizada com sucesso.");
    }


}
