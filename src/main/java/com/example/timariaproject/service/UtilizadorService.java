package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.RegistoDTO;
import com.example.timariaproject.DTOs.UserDTO;
import com.example.timariaproject.DTOs.UserEditDTO;
import com.example.timariaproject.domain.UserCredentials;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.exception.EmailAlreadyExistsException;
import com.example.timariaproject.repository.UserCredentialsRepository;
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
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserCredentialsService userCredentialsService;

    public Iterable<Utilizador> getAll() {
        return utilizadorRepository.findAll();
    }

    public String addNewUtilizador(RegistoDTO registoDTO) {
        Utilizador user = new Utilizador();
        if (utilizadorRepository.findByEmail(registoDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("O email já está registado.");
        }
        user.setNome(registoDTO.getNome());
        user.setEmail(registoDTO.getEmail());
        user.setTipoutilizador(registoDTO.getTipoutilizador());
        user.setNif(registoDTO.getNif());
        if ("Produtor".equals(registoDTO.getTipoutilizador())) {
            user.setMoradafiscal(registoDTO.getMoradafiscal());
        }
        if (registoDTO.getTelefone() != null) {
            user.setTelefone(registoDTO.getTelefone());
        }
        utilizadorRepository.save(user);

        userCredentialsService.addNewUtilizador(registoDTO.getEmail(), registoDTO.getPassword());


        return "Saved";
    }

    public UserDTO getUserDetailsByEmail() {
        // Vai buscar qual é o user que está autenticado
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return utilizadorRepository.findByEmail(user.getUsername())
                .map(utilizador ->
                        new UserDTO(utilizador.getNome(), utilizador.getEmail(),
                                utilizador.getTelefone(), utilizador.getNif(), utilizador.getTipoutilizador(),
                                utilizador.getMoradafiscal(), utilizador.getFotoperfil(), utilizador.getDescricao()))
                .orElseThrow();
    }

    public String updateUserDetails(UserEditDTO userEditDTO) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var utilizador = utilizadorRepository.findByEmail(user.getUsername()).orElseThrow();
        utilizador.setNome(userEditDTO.getNome());
        utilizador.setTelefone(userEditDTO.getTelefone());
        utilizador.setFotoperfil(userEditDTO.getFotoperfil());
        utilizador.setDescricao(userEditDTO.getDescricao());
        utilizador.setMoradafiscal(userEditDTO.getMoradafiscal());

        utilizadorRepository.save(utilizador);

        return "Edit User details successful";
    }

    public String showUserProfilePic() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var utilizador = utilizadorRepository.findByEmail(user.getUsername()).orElseThrow();

        return utilizador.getFotoperfil();
    }

}
