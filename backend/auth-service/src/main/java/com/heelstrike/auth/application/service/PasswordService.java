package com.heelstrike.auth.application.service;

import com.heelstrike.core.BaseService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService extends BaseService {
    private final Argon2 argon2 = Argon2Factory.create();

    /**
     * NOTE: The Argon2 algorithm automatically handles salting, no need for custom
     * implementation for the current use case (fine-grain control not currently
     * required).
     *
     * @param password String, plaintext password.
     * @return String, salted password hash.
     * */
    public String hashPassword(String password) {

        return argon2.hash(12,65536, 1, password.toCharArray());
    }

    public boolean verifyPassword(String passwordHash, String passwordPlainText) {
        return argon2.verify(passwordHash, passwordPlainText.toCharArray());
    }

}
