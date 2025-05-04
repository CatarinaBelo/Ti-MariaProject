package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.CarrinhoDTO;
import com.example.timariaproject.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping("/add/{idUtilizador}/{idAnuncio}")
    public ResponseEntity<String> adicionarAoCarrinho(
            @PathVariable Integer idUtilizador,
            @PathVariable Integer idAnuncio,
            @RequestParam int quantidade
    ) {
        String mensagem = carrinhoService.addToCarrinho(idUtilizador, idAnuncio, quantidade);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/remove/{idCarrinho}")
    public ResponseEntity<Void> removerDoCarrinho(@PathVariable Integer idCarrinho) {
        carrinhoService.removeFromCarrinho(idCarrinho);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/{idUtilizador}")
    public ResponseEntity<List<CarrinhoDTO>> listarCarrinho(@PathVariable Integer idUtilizador) {
        return ResponseEntity.ok(carrinhoService.listUsersCarrinho(idUtilizador));
    }
}
