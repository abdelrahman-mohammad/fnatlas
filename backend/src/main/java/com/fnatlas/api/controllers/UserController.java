package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.user.UserCreateRequest;
import com.fnatlas.api.dtos.user.UserUpdateRequest;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.services.AuthService;
import com.fnatlas.api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid UserCreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(id, token);
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(@RequestHeader(value = "Authorization") String token) {
        authService.getCurrentUser(token);
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest request, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(id, token);
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(id, token);
        userService.deleteUser(id);
    }

}