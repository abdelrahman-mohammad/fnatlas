package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.auth.LoginRequest;
import com.fnatlas.api.dtos.auth.LoginResponse;
import com.fnatlas.api.dtos.auth.LogoutRequest;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody @Valid LogoutRequest token) {
        authService.logout(token.getToken());
    }

    @GetMapping("/me")
    public User getCurrentUser(@RequestHeader("Authorization") String token) {
        System.out.println("Token: " + token);
        return authService.getCurrentUser(token);
    }
}
