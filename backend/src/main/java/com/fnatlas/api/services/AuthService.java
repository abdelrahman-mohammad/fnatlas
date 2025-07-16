package com.fnatlas.api.services;

import com.fnatlas.api.dtos.LoginResponse;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.entities.UserSession;
import com.fnatlas.api.exceptions.AuthenticationFailedException;
import com.fnatlas.api.exceptions.UserNotFoundException;
import com.fnatlas.api.repositories.UserRepository;
import com.fnatlas.api.repositories.UserSessionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserSessionsRepository userSessionsRepository;
    private final UserRepository userRepository;

    public UserSession createUserSession(User user) {
        UserSession userSession = new UserSession(user);
        return userSessionsRepository.save(userSession);
    }

    public LoginResponse login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("username", username));

        if (!user.getPassword().equals(password)) throw new AuthenticationFailedException();

        UserSession userSession = createUserSession(user);
        return new LoginResponse(userSession.getToken(), user);
    }

    public void logout(String token) {
        UserSession userSession = userSessionsRepository.findByToken(token)
                .orElseThrow(() -> new AuthenticationFailedException("Invalid token"));

        userSessionsRepository.delete(userSession);
    }

    public User getCurrentUser(String token) {
        UserSession userSession = userSessionsRepository.findByToken(token)
                .orElseThrow(() -> new AuthenticationFailedException("Invalid token"));

        if(userSession.getExpiresAt().isBefore(LocalDateTime.now())) {
            userSessionsRepository.delete(userSession);
            throw new AuthenticationFailedException("Session expired");
        }

        return userRepository.findById(userSession.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException(userSession.getUser().getId()));
    }

}
