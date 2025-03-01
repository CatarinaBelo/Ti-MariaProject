package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.ProductDTO;
import com.example.timariaproject.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/product")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<ProductDTO>> getProductDetails(){
        return ResponseEntity.ok(produtoService.getAllProducts());
    }
}
