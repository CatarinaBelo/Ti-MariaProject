package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.ProductDTO;
import com.example.timariaproject.DTOs.UserDTO;
import com.example.timariaproject.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public List<ProductDTO> getAllProducts() {
        return produtoRepository.findAll()
                .stream()
                .map(produto -> new ProductDTO(produto.getNomeproduto(), produto.getTipoproduto(), produto.getStock(),
                        produto.getAnuncio(), produto.getCategoriaproduto(), produto.getTags(),
                        produto.getIdsubcategoria()))
                .toList();
    }

}
