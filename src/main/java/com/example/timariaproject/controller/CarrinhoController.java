package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.CarrinhoBatchDTO;
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

    @PostMapping("/add-batch")
    public ResponseEntity<List<String>> adicionarAoCarrinhoBatch(@RequestBody CarrinhoBatchDTO batchDTO) {
        System.out.println("ID Utilizador recebido: " + batchDTO.getIdUtilizador());
        System.out.println("Itens recebidos:");
        if (batchDTO.getItens() != null) {
            batchDTO.getItens().forEach(item ->
                    System.out.println("ID Anúncio: " + item.getIdAnuncio() + ", Quantidade: " + item.getQuantidade())
            );
        } else {
            System.out.println("Itens é null!");
        }

        List<String> mensagens = carrinhoService.addItensToCarrinho(batchDTO);
        return ResponseEntity.ok(mensagens);
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
