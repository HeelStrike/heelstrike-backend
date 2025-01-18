package com.superlapp.auth.domain.dto;

import com.superlapp.core.BaseDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class UserDTO {

    private UUID uuid;
    private String username;
    private String passwordHash;
    private String primaryEmail;
    private String secondaryEmail;
    private int mobile;
    private Set<String> roles;

    public UserDTO() {}

    public void setUuid(UUID newUuid) {
        this.uuid = newUuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPasswordHash(String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPrimaryEmail(String newPrimaryEmail) {
        this.primaryEmail = newPrimaryEmail;
    }

    public String getPrimaryEmail() {
        return this.primaryEmail;
    }

    public void setSecondaryEmail(String newSecondaryEmail) {

    }

    public String getSecondaryEmail() {
        return this.secondaryEmail;
    }

    public void setMobile(int newMobile) {
        this.mobile = newMobile;
    }

    public int getMobile() {
        return this.mobile;
    }

    public void setRole(Set<String> newRoles) {
        this.roles = newRoles;
    }

    public Set<String> getRole() {
        return this.roles;
    }

}
