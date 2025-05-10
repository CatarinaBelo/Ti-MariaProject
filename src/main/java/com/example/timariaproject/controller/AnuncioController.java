package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.DTOs.AnuncioEditDTO;
import com.example.timariaproject.DTOs.AnuncioSaveDTO;
import com.example.timariaproject.DTOs.UserEditDTO;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping("/search/filter")
    public ResponseEntity<Page<AnuncioDTO>> searchAnuncios(
            @RequestParam(required = false) List<Integer> categoriaIds,
            @RequestParam(required = false) List<Integer> subcategoriaIds,
            @RequestParam(required = false) List<Integer> distritoIds,
            @RequestParam(required = false) List<Integer> tagIds,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<AnuncioDTO> result = anuncioService.searchAnunciosByFilters(
                categoriaIds, subcategoriaIds, distritoIds, tagIds, page, size
        );
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search/bar")
    public ResponseEntity<Page<AnuncioDTO>> searchBarAnuncios(
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

    @GetMapping("/anunciodetails/{id}")
    public ResponseEntity<AnuncioDTO> getAnuncioById(@PathVariable Integer id) {
        AnuncioDTO anuncioDTO = anuncioService.getAnuncioDetails(id);
        return ResponseEntity.ok(anuncioDTO);
    }


    @PostMapping(path = "/edit/{id}")
    public ResponseEntity<String> editAnuncioDetails(
            @PathVariable Integer id,
            @RequestBody AnuncioEditDTO anuncioEditDTO
    ) {
        anuncioService.updateAnuncio(id, anuncioEditDTO);
        return ResponseEntity.ok("Edit Succeeded");
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(
            @PathVariable Integer id,
            @RequestParam int novoStock) {

        try {
            anuncioService.updateStock(id, novoStock);
            return ResponseEntity.ok("Stock atualizado com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anúncio não encontrado.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/utilizador/{userId}")
    public ResponseEntity<List<AnuncioDTO>> getAnunciosByUser(@PathVariable Integer userId) {
        List<AnuncioDTO> anuncios = anuncioService.getAnunciosByUser(userId);
        return ResponseEntity.ok(anuncios);
    }
}
