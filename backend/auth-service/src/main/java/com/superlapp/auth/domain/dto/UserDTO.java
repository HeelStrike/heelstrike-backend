package com.superlapp.auth.domain.dto;

import com.superlapp.core.BaseDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class UserDTO {

    private UUID uuid;
    private String username;
    private Set<String> roles;

    public UserDTO() {}

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID newUuid) {
        this.uuid = newUuid;
    }

    public String getName() {
        return this.username;
    }

    public void setName(String newUsername) {
        this.username = newUsername;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> newRoles) {
        this.roles = newRoles;
    }

}
