package com.superlapp.auth.application.service;

import com.superlapp.auth.domain.dto.UserDTO;
import com.superlapp.auth.domain.entity.RoleEntity;
import com.superlapp.auth.domain.entity.UserEntity;
import com.superlapp.auth.domain.repository.RoleRepository;
import com.superlapp.auth.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    PasswordService passwordService;

    public UserService() {
    }

    @Transactional
    public void createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        setPassword(userDTO);

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

    @Transactional
    public void updateUser(UserDTO userDTO) {
        // TODO: Implement, find UserEntity, set userEntity = userDTO, send success or error message.

        UserEntity userEntity = userRepository.findByUuid(userDTO.getUuid())
                .orElseThrow(() -> new RuntimeException("Cannot delete user, does not exist."));;

        if (userDTO.getUsername() != null) {
            userEntity.setUsername(userDTO.getUsername());
        }

        if (userDTO.getPassword() != null) {
            setPassword(userDTO);
        }

        if (userDTO.getPrimaryEmail() != null) {
            userEntity.setPrimaryEmail(userDTO.getPrimaryEmail());
        }

        if (userDTO.getSecondaryEmail() != null) {
            userEntity.setSecondaryEmail(userDTO.getSecondaryEmail());
        }

        if (userDTO.getMobile() != -1) {
            userEntity.setMobile(userDTO.getMobile());
        }

        userRepository.persistUserEntity(userEntity);

    }

    private void setPassword(UserDTO userDTO) {
        try {
            String passwordHash = passwordService.hashPassword(userDTO.getPassword());

            if (!passwordService.verifyPassword(passwordHash, userDTO.getPassword())) {
                throw new RuntimeException("UserDTO supplied password and argon2 password hash do not match.");
            }

            userDTO.setPasswordHash(passwordHash);

        } catch (Exception e) {
            throw new RuntimeException("Unable to hash password, " + e.getMessage(), e);
        }
    }

    @Transactional
    public void updateUserRole(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getUuid());
    }

    @Transactional
    public void deleteUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Cannot delete user, does not exist."));

        try {
            userRepository.delete(userEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
