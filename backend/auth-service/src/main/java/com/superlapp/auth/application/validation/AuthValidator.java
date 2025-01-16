package com.superlapp.auth.application.validation;

import com.superlapp.auth.domain.dto.UserDTO;
import com.superlapp.auth.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class AuthValidator {
    private UUID userUuid;
    private String username;
    private Set<String> roles;

    @Inject
    UserRepository userRepository;

    public AuthValidator(UserDTO userDTO) {
        this.userUuid = userDTO.getUuid();
        this.username = userDTO.getName();
        this.roles = userDTO.getRoles();
    }

    public boolean validateUser() {
        return userUuid == userRepository.findUserEntity(userUuid).getUuid();
    }

}
