package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.RegistoDTO;
import com.example.timariaproject.DTOs.UserDTO;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    public Iterable<Utilizador> getAll() {
        return utilizadorRepository.findAll();
    }

    public String addNewUtilizador(RegistoDTO registoDTO) {
        Utilizador user = new Utilizador();
        if (utilizadorRepository.findByEmail(registoDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException();
        }
        user.setNome(registoDTO.getNome());
        user.setEmail(registoDTO.getEmail());
        user.setTipoutilizador(registoDTO.getTipoutilizador());
        user.setNif(registoDTO.getNif());
        if ("agricultor".equals(registoDTO.getTipoutilizador())) {
            user.setMoradafiscal(registoDTO.getMoradafiscal());
        }
        if (registoDTO.getTelefone() != null) {
            user.setTelefone(registoDTO.getTelefone());
        }
        utilizadorRepository.save(user);
        return "Saved";
    }

    public UserDTO getUserDetailsByEmail() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return utilizadorRepository.findByEmail(user.getUsername())
                .map(utilizador ->
                        new UserDTO(utilizador.getNome(), utilizador.getEmail(),
                                utilizador.getTelefone(), utilizador.getNif(), utilizador.getTipoutilizador(),
                                utilizador.getMoradafiscal(), utilizador.getFotoperfil(), utilizador.getDescricao()))
                .orElseThrow();
    }
}
