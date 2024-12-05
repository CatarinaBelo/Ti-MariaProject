package com.example.timariaproject.service;

import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.transform.sax.SAXResult;

@Service
@RequiredArgsConstructor
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    public Iterable<Utilizador> getAll(){
        return utilizadorRepository.findAll();
    }

    public String addNewUtilizador(String nome, String email, String tipoutilizador){
        Utilizador user = new Utilizador();
        user.setNome(nome);
        user.setEmail(email);
        user.setTipoutilizador(tipoutilizador);
        utilizadorRepository.save(user);
        return "Saved";
    }
}
