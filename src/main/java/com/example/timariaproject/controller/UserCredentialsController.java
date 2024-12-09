package com.example.timariaproject.controller;

import com.example.timariaproject.domain.UserCredentials;
import com.example.timariaproject.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/userCredentials")
public class UserCredentialsController {
    @Autowired
    private UserCredentialsService userCredentialsService;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUtilizador(@RequestParam String nome, @RequestParam String email) {
        return userCredentialsService.addNewUtilizador(nome, email);
    }


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<UserCredentials> getAllUsers() {
        System.out.print(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userCredentialsService.getAll();
    }


}
