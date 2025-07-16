package com.fnatlas.api.services;

import com.fnatlas.api.entities.User;
import com.fnatlas.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User userUpdates) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;

        if (userUpdates.getUsername() != null) {
            if (userRepository.existsByUsername(userUpdates.getUsername()) && !user.getUsername().equals(userUpdates.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            user.setUsername(userUpdates.getUsername());
        }
        if (userUpdates.getEmail() != null) {
            if(userRepository.existsByEmail(userUpdates.getEmail()) && !user.getEmail().equals(userUpdates.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            user.setEmail(userUpdates.getEmail());
        }
        if (userUpdates.getPassword() != null) {
            user.setPassword(userUpdates.getPassword());
        }

        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}