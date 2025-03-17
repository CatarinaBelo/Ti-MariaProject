package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.CategoriaDTO;
import com.example.timariaproject.domain.Categoria;
import com.example.timariaproject.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }
}
