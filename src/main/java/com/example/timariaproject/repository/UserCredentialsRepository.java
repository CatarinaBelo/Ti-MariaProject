package com.example.timariaproject.repository;

import com.example.timariaproject.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByEmail(String email);
}
