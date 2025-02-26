package com.heelstrike.auth.application.service;

import com.heelstrike.core.BaseService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * <h2>PasswordService</h2>
 * Service for handling password hashing and verification using the Argon2
 * algorithm.
 *
 * <p>Argon2 is used as the password hashing algorithm. It's important to note
 * that Argon2 handles salting so there's no need for additional salting logic.</p>
 *
 * @author Oliver Keefe
 * @since 1.0
 * */
@ApplicationScoped
public class PasswordService extends BaseService {
    private final Argon2 argon2 = Argon2Factory.create();

    /**
     * <h3>hashPassword</h3>
     * Method responsible for hashing a plaintext password using Argon2 algorithm.
     *
     * @param password The plaintext password for hashing.
     * @return Salted and Hashed password.
     * @since 1.0
     * */
    public String hashPassword(String password) {

        return argon2.hash(12,65536, 1, password.toCharArray());
    }

    /**
     * <h3>verifyPassword</h3>
     * Method for comparing a plaintext password against it's hashed counterpart.
     * This is useful for both,
     *
     * <li>
     *     <ul>1. Validating login.</ul>
     *     <ul>2. Validating successful, lossless hashing of a plaintext password.</ul>
     * </li>
     *
     * @param passwordHash The stored password hash value.
     * @param passwordPlainText The plaintext password for verification.
     * @return {@code true} if the plaintext password matches the stored password when converted into plaintext {@code false} otherwise.
     * @since 1.0
     * */
    public boolean verifyPassword(String passwordHash, String passwordPlainText) {
        return argon2.verify(passwordHash, passwordPlainText.toCharArray());
    }

}
