package com.example.timariaproject.controller;

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

@Controller // This means that this class is a Controller
@RequestMapping(path = "/user")
public class UtilizadorController {
    @Autowired
    private UtilizadorService utilizadorService;


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Utilizador> getAllUsers() {
        return utilizadorService.getAll();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> showRegistrationForm(@RequestBody RegistoDTO registoDTO) {
        utilizadorService.addNewUtilizador(registoDTO);
        return ResponseEntity.ok().body("Register Succeeded");
    }

    @GetMapping(path = "/details")
    public ResponseEntity<UserDTO> getUserDetails() {
        return ResponseEntity.ok(utilizadorService.getUserDetailsByEmail());
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<String> editUserDetails(@RequestBody UserEditDTO userEditDTO) {
        utilizadorService.updateUserDetails(userEditDTO);
        return ResponseEntity.ok().body("Edit Succeeded");
    }

}

