package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.LoginRequest;
import com.fnatlas.api.dtos.LoginResponse;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody String token) { // Could create a DTO for LogoutRequest, but it would just have a single field for token (so not necessary?)
        authService.logout(token);
    }

    @GetMapping("/me")
    public User getCurrentUser(@RequestHeader("Authorization") String token) {
        return authService.getCurrentUser(token);
    }
}
