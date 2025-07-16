package com.fnatlas.api.repositories;

import com.fnatlas.api.entities.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSessionsRepository extends JpaRepository<UserSession, Long> {
    Optional<UserSession> findByToken(String token);
    boolean existsByToken(String token);
    void deleteByToken(String token);
    void deleteByUserId(Long userId);
}