package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.SubcategoriaDTO;
import com.example.timariaproject.domain.Subcategoria;
import com.example.timariaproject.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/subcategoria")
public class SubcategoriaController {
    @Autowired
    private SubcategoriaService subcategoriaService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<SubcategoriaDTO>> listarSubcategorias() {
        List<SubcategoriaDTO> subcategorias = subcategoriaService.getAllSubcategorias();
        return ResponseEntity.ok(subcategorias);
    }
}
