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

@Controller // This means that this class is a Controller
@RequestMapping(path = "/login")
public class LoginController {
    @Autowired
    private UtilizadorService utilizadorService;


    @GetMapping()
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("success");
    }

}

