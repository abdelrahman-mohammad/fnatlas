package com.fnatlas.api.services;

import com.fnatlas.api.dtos.UserRequest;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.exceptions.EntityNotFoundException;
import com.fnatlas.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername()))
            throw new IllegalArgumentException("Username already exists");

        if (userRepository.existsByEmail(userRequest.getEmail()))
            throw new IllegalArgumentException("Email already exists");

        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User", id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UserRequest userUpdates) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User", id));

        if (userUpdates.getUsername() != null) user.setUsername(userUpdates.getUsername());
        if (userUpdates.getEmail() != null) user.setEmail(userUpdates.getEmail());
        if (userUpdates.getPassword() != null) user.setPassword(userUpdates.getPassword());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) throw new EntityNotFoundException("User", id);
        userRepository.deleteById(id);
    }
}