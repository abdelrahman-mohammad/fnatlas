package com.fnatlas.api.services;

import com.fnatlas.api.entities.User;
import com.fnatlas.api.entities.UserSession;
import com.fnatlas.api.repositories.UserRepository;
import com.fnatlas.api.repositories.UserSessionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserSessionsRepository userSessionsRepository;
    private final UserRepository userRepository;

    public UserSession createAuthToken(User user) {
        UserSession userSession = new UserSession(user);
        return userSessionsRepository.save(userSession);
    }


}
