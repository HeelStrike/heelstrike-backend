package com.superlapp.auth.application.service;

import com.superlapp.core.BaseService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService extends BaseService {
    private final Argon2 argon2 = Argon2Factory.create();

    public String hashPassword(String password) {
        return argon2.hash(12,65536, 1, password.toCharArray());
    }

    public boolean verifyPassword(String paswordHash, String passwordPlainText) {
        return argon2.verify(paswordHash, passwordPlainText.toCharArray());
    }

}
