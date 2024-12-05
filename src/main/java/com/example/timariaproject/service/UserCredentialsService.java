package com.example.timariaproject.service;

import com.example.timariaproject.domain.UserCredentials;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.UserCredentialsRepository;
import com.example.timariaproject.repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCredentialsService {

    private final UserCredentialsRepository userCredentialsRepository;


    public Iterable<UserCredentials> getAll(){
        return userCredentialsRepository.findAll();
    }

    public String addNewUtilizador(String email, String password){
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail(email);
        userCredentials.setPassword(password);
        userCredentialsRepository.save(userCredentials);
        return "Saved";
    }


}
