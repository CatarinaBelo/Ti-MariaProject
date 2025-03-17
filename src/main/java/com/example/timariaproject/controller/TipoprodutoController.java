package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.SubcategoriaDTO;
import com.example.timariaproject.DTOs.TipoprodutoDTO;
import com.example.timariaproject.service.SubcategoriaService;
import com.example.timariaproject.service.TipoprodutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/tipoproduto")
public class TipoprodutoController {
    @Autowired
    private TipoprodutoService tipoprodutoService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<TipoprodutoDTO>> listarTipoproduto() {
        List<TipoprodutoDTO> tipoprodutos = tipoprodutoService.getAllTipoprodutos();
        return ResponseEntity.ok(tipoprodutos);
    }
}
