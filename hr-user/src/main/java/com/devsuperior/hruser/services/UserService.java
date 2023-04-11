package com.devsuperior.hruser.services;

import com.devsuperior.hruser.entities.User;
import com.devsuperior.hruser.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(final Long userId) {
        return this.userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User com id { %s } não encontrado", userId)));
    }

    public User findUserByEmail(final String userEmail) {
        return this.userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User com email { %s } não encontrado", userEmail)));
    }

}
