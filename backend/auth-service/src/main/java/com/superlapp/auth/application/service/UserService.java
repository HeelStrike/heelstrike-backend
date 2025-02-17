package com.superlapp.auth.application.service;

import com.superlapp.auth.domain.dto.UserDTO;
import com.superlapp.auth.domain.entity.RoleEntity;
import com.superlapp.auth.domain.entity.UserEntity;
import com.superlapp.auth.domain.repository.RoleRepository;
import com.superlapp.auth.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import javax.management.relation.Role;

@ApplicationScoped
public class UserCreationService {

    @Inject
    UserRepository userRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    PasswordService passwordService;

    public UserCreationService() {
    }

    @Transactional
    public void createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        try {
            String passwordHash = passwordService.hashPassword(userDTO.getPassword());

            if (!passwordService.verifyPassword(passwordHash, userDTO.getPassword())) {
                throw new RuntimeException("UserDTO supplied password and argon2 password hash do not match.");
            }

            userDTO.setPasswordHash(passwordHash);

        } catch (Exception e) {
            throw new RuntimeException("Unable to hash password, " + e.getMessage(), e);
        }

        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPasswordHash(userDTO.getPasswordHash());
        userEntity.setMobile(userDTO.getMobile());
        userEntity.setPrimaryEmail(userDTO.getPrimaryEmail());

        if (userDTO.getSecondaryEmail() != null) {
            userEntity.setSecondaryEmail(userDTO.getSecondaryEmail());
        }

        RoleEntity roleEntity;

        if (userDTO.getRoleId() != null) {
            roleEntity = roleRepository.findRoleById(userDTO.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role with ID " + userDTO.getRoleId() + " not found."));
        } else {
            roleEntity = roleRepository.findRoleById(1L)
                    .orElseThrow(() -> new RuntimeException("Default role not found in database. Expecting | id: 1 | role_name: 'user' |"));
        }

        userEntity.setRole(roleEntity);

        try {
            userRepository.persistUserEntity(userEntity);

        } catch (Exception e) {
            throw new RuntimeException("Could not create new user, " + e.getMessage(), e);
        }
    }

    public void updateUserRole(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getUuid());
    }
}
