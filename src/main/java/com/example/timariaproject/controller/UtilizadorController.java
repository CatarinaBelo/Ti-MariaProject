package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.DTOs.RegistoDTO;
import com.example.timariaproject.DTOs.UserDTO;
import com.example.timariaproject.DTOs.UserEditDTO;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/user")
public class UtilizadorController {
    @Autowired
    private UtilizadorService utilizadorService;


    @GetMapping(path = "/details")
    public ResponseEntity<UserDTO> getUserDetails() {
        return ResponseEntity.ok(utilizadorService.getUserDetailsByEmail());
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<String> editUserDetails(@RequestBody UserEditDTO userEditDTO) {
        utilizadorService.updateUserDetails(userEditDTO);
        return ResponseEntity.ok().body("Edit Succeeded");
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> showRegistrationForm(@RequestBody RegistoDTO registoDTO) {
        utilizadorService.addNewUtilizador(registoDTO);
        return ResponseEntity.ok().body("Register Succeeded");
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Utilizador> getAllUsers() {
        return utilizadorService.getAll();
    }

    @GetMapping(path = "/id")
    public ResponseEntity<Integer> getUserId(@RequestParam String email) {
        return ResponseEntity.ok(utilizadorService.getUserId(email));
    }

    @GetMapping("/produtores")
    public ResponseEntity<List<UserDTO>> getAllProdutores() {
        List<UserDTO> produtores = utilizadorService.getUtilizadoresProdutores();
        return ResponseEntity.ok(produtores);
    }

    @GetMapping("/detalhesUser/{id}")
    public ResponseEntity<UserDTO> getUtilizadorById(@PathVariable Integer id) {
        UserDTO user = utilizadorService.getUtilizadorById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/showpic")
    public ResponseEntity<String> showProfilePic() {
        return ResponseEntity.ok(utilizadorService.showUserProfilePic());
    }

    //  Add an anuncio to favorites
    @PostMapping("/{userId}/add/favoritos/{anuncioId}")
    public ResponseEntity<String> addFavorite(@PathVariable Integer userId, @PathVariable Integer anuncioId) {
        utilizadorService.addFavoriteAnuncio(userId, anuncioId);
        return ResponseEntity.ok("Anuncio added to favorites");
    }

    //  Remove an anuncio from favorites
    @DeleteMapping("/{userId}/remove/favoritos/{anuncioId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Integer userId, @PathVariable Integer anuncioId) {
        utilizadorService.removeFavoriteAnuncio(userId, anuncioId);
        return ResponseEntity.ok("Anuncio removed from favorites");
    }

    //  Get all favorite anuncios of a user
    @GetMapping("/{userId}/get/favoritos")
    public ResponseEntity<List<AnuncioDTO>> getFavorites(@PathVariable Integer userId) {
        List<AnuncioDTO> favoritos = utilizadorService.getUserFavorites(userId);
        return ResponseEntity.ok(favoritos);
    }

    //  Check if an anuncio is in the user's favorites
    @GetMapping("/{userId}/check/favoritos/{anuncioId}")
    public ResponseEntity<Boolean> isFavorite(@PathVariable Integer userId, @PathVariable Integer anuncioId) {
        boolean isFavorite = utilizadorService.isFavorite(userId, anuncioId);
        return ResponseEntity.ok(isFavorite);
    }

}

