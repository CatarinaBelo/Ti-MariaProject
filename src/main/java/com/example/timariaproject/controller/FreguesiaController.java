package com.example.timariaproject.controller;

import com.example.timariaproject.DTOs.ConcelhoDTO;
import com.example.timariaproject.DTOs.FreguesiaDTO;
import com.example.timariaproject.service.ConcelhoService;
import com.example.timariaproject.service.FreguesiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/freguesia")
public class FreguesiaController {
    @Autowired
    private FreguesiaService freguesiaService;

    @GetMapping(path = "/details")
    public ResponseEntity<List<FreguesiaDTO>> listarFreguesias() {
        List<FreguesiaDTO> freguesias = freguesiaService.getAllFreguesias();
        return ResponseEntity.ok(freguesias);
    }
}
