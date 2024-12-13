package com.example.timariaproject.security;

import com.example.timariaproject.domain.UserCredentials;
import com.example.timariaproject.domain.Utilizador;
import com.example.timariaproject.repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.timariaproject.repository.UserCredentialsRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final UtilizadorRepository utilizadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userCredentialsOptional = userCredentialsRepository.findByEmail(username);
        var utilizadorOptional = utilizadorRepository.findByEmail(username);
        var utilizadorRole = utilizadorOptional.map(Utilizador::getTipoutilizador).orElse(null);

        if (userCredentialsOptional.isEmpty()) {
            return null;
        }
        var user = userCredentialsOptional.orElse(new UserCredentials());
        return User.builder()
                .username(username)
                .password(user.getPassword())
                .roles(utilizadorRole)
                .build();
    }
}