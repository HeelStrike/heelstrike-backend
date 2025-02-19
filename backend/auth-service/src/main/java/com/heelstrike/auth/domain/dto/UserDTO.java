package com.heelstrike.auth.domain.dto;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

public class UserDTO {

    private UUID uuid;
    private String username;
    // Plain Text Password:
    private String password;
    private String passwordHash;
    private String primaryEmail;
    private String secondaryEmail;
    private long mobile;
    private Long roleId;

    public UserDTO() {}

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPrimaryEmail() {
        return this.primaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getSecondaryEmail() {
        return this.secondaryEmail;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public long getMobile() {
        return this.mobile;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }
}
