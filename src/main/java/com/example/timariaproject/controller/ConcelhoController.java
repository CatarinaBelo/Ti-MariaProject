package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.ConcelhoDTO;
import com.example.timariaproject.DTOs.DistritoDTO;
import com.example.timariaproject.service.ConcelhoService;
import com.example.timariaproject.service.DistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/concelho")
public class ConcelhoController {
    @Autowired
    private ConcelhoService concelhoService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<ConcelhoDTO>> listarConcelhos() {
        List<ConcelhoDTO> concelhos = concelhoService.getAllConcelhos();
        return ResponseEntity.ok(concelhos);
    }
}
