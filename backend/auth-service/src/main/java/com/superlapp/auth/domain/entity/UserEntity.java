package com.superlapp.auth.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column (name = "username")
    private String username;

    @Column (name = "password_hash")
    private String passwordHash;

    @Column (name = "primary_email")
    private String primaryEmail;

    @Column (name = "secondary_email")
    private String secondaryEmail;

    @Column (name = "mobile")
    private int mobile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private RoleEntity role;

    public void setUuid(UUID newUuid) {
        this.uuid = newUuid;
    }

    public UUID getUuid () {
        return this.uuid;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPrimaryEmail(String newPrimaryEmail) {
        this.primaryEmail = newPrimaryEmail;
    }

    public String getPrimaryEmail() {
        return this.primaryEmail;
    }

    public void setSecondaryEmail(String newSecondaryEmail) {
        this.secondaryEmail = newSecondaryEmail;
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

    public void setRole(RoleEntity newRoleEntity) {
        this.role = newRoleEntity;
    }

    public RoleEntity getRole() {
        return this.role;
    }
}
