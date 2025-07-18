package com.fnatlas.api.services;

import com.fnatlas.api.dtos.auth.LoginRequest;
import com.fnatlas.api.dtos.auth.LoginResponse;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.entities.UserSession;
import com.fnatlas.api.exceptions.AuthenticationFailedException;
import com.fnatlas.api.exceptions.EntityNotFoundException;
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

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User", "username", loginRequest.getUsername()));

        if (!user.getPassword().equals(loginRequest.getPassword())) throw new AuthenticationFailedException();

        UserSession userSession = UserSession.builder().user(user).build();
        userSessionsRepository.save(userSession);

        return new LoginResponse(userSession.getToken(), user);
    }

    public void logout(String token) {
        System.out.println("Logging out user with token: " + token);
        UserSession userSession = userSessionsRepository.findByToken(token)
                .orElseThrow(() -> new AuthenticationFailedException("Invalid token"));

        userSessionsRepository.delete(userSession);
    }

    public User getCurrentUser(String token) {
        UserSession userSession = userSessionsRepository.findByToken(token)
                .orElseThrow(() -> new AuthenticationFailedException("You must be logged in to access this resource"));

        if(userSession.getExpiresAt().isBefore(LocalDateTime.now())) {
            userSessionsRepository.delete(userSession);
            throw new AuthenticationFailedException("Session expired. Please log in again.");
        }

        return userRepository.findById(userSession.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User", userSession.getUser().getId()));
    }

    public void verifyAuthorization(Long requestedUserId, String token) {
        User currentUser = getCurrentUser(token);
        if (!currentUser.getId().equals(requestedUserId)) {
            throw new AuthenticationFailedException("User with ID: " + requestedUserId);
        }
    }
}
