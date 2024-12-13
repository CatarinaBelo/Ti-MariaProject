package com.example.timariaproject.service;

import com.example.timariaproject.domain.UserCredentials;
import com.example.timariaproject.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCredentialsService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;


    public Iterable<UserCredentials> getAll(){
        return userCredentialsRepository.findAll();
    }

    public String addNewUtilizador(String email, String password){
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail(email);
        userCredentials.setPassword(passwordEncoder.encode(password));
        userCredentialsRepository.save(userCredentials);
        return "Saved";
    }


}
