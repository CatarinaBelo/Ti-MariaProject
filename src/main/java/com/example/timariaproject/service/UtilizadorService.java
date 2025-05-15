package com.example.timariaproject.service;

import com.example.timariaproject.DTOs.AnuncioDTO;
import com.example.timariaproject.DTOs.RegistoDTO;
import com.example.timariaproject.DTOs.UserDTO;
import com.example.timariaproject.DTOs.UserEditDTO;
import com.example.timariaproject.domain.Anuncio;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.exception.EmailAlreadyExistsException;
import com.example.timariaproject.repository.AnuncioRepository;
import com.example.timariaproject.repository.UserCredentialsRepository;
import com.example.timariaproject.repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserCredentialsService userCredentialsService;
    private final AnuncioRepository anuncioRepository;

    public UserDTO getUserDetailsByEmail() {
        // Vai buscar qual é o user que está autenticado
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return utilizadorRepository.findByEmail(user.getUsername())
                .map(utilizador ->
                        new UserDTO(utilizador.getId(), utilizador.getNome(), utilizador.getEmail(),
                                utilizador.getTelefone(), utilizador.getNif(), utilizador.getTipoutilizador(),
                                utilizador.getMoradafiscal(), utilizador.getFotoperfil(), utilizador.getDescricao()))
                .orElseThrow();
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

    public Integer getUserId(String email) {
        return utilizadorRepository.findByEmail(email)
                .map(Utilizador::getId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado para o email: " + email));
    }


    public Iterable<Utilizador> getAll() {
        return utilizadorRepository.findAll();
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

    public List<UserDTO> getUtilizadoresProdutores() {
        List<Utilizador> produtores = utilizadorRepository.findByTipoutilizador("Produtor");
        return produtores.stream()
                .map(utilizador ->
                        new UserDTO(utilizador.getId(), utilizador.getNome(), utilizador.getEmail(),
                                utilizador.getTelefone(), utilizador.getNif(), utilizador.getTipoutilizador(),
                                utilizador.getMoradafiscal(), utilizador.getFotoperfil(), utilizador.getDescricao()))
                .collect(Collectors.toList());
    }

    public UserDTO getUtilizadorById(Integer id) {
        Utilizador utilizador = utilizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado com ID: " + id));
        return new UserDTO(utilizador.getId(), utilizador.getNome(), utilizador.getEmail(),
                utilizador.getTelefone(), utilizador.getNif(), utilizador.getTipoutilizador(),
                utilizador.getMoradafiscal(), utilizador.getFotoperfil(), utilizador.getDescricao());
    }

    //  Add an anuncio to favorites
    public void addFavoriteAnuncio(Integer userId, Integer anuncioId) {
        Utilizador user = utilizadorRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Anuncio anuncio = anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anuncio not found"));

        // Add anuncio to user's favorite list
        if (!user.getFavoritos().contains(anuncio)) {
            user.getFavoritos().add(anuncio);
            utilizadorRepository.save(user);
        }
    }

    //  Remove an anuncio from favorites
    public void removeFavoriteAnuncio(Integer userId, Integer anuncioId) {
        Utilizador user = utilizadorRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Anuncio anuncio = anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anuncio not found"));

        user.getFavoritos().remove(anuncio);
        utilizadorRepository.save(user);
    }

    //  Get user's favorite anuncios
    public List<AnuncioDTO> getUserFavorites(Integer userId) {
        Utilizador user = utilizadorRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getFavoritos()
                .stream()
                .map(Anuncio::toDto)
                .toList();
    }

    //  Check if an anuncio is in the user's favorites
    public boolean isFavorite(Integer userId, Integer anuncioId) {
        Utilizador user = utilizadorRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getFavoritos()
                .stream()
                .anyMatch(anuncio -> anuncio.getId().equals(anuncioId));
    }

}
