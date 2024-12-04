package com.example.timariaproject.controller;

import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.UtilizadorRepository;
import com.example.timariaproject.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/user")
public class UtilizadorController {
    @Autowired
    private UtilizadorService utilizadorService;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUtilizador(@RequestParam String nome, @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return utilizadorService.addNewUtilizador(nome, email);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Utilizador> getAllUsers() {
        return utilizadorService.getAll();
    }
}

