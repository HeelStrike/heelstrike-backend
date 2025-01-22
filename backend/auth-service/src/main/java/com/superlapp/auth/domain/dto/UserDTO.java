package com.superlapp.auth.domain.dto;

import com.superlapp.auth.domain.entity.RoleEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.UUID;

@ApplicationScoped
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

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword() {
        return this.password;
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

    public void setMobile(long newMobile) {
        this.mobile = newMobile;
    }

    public long getMobile() {
        return this.mobile;
    }

    public void setRoleId(Long newRoleId) {
        this.roleId = newRoleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

}
