package com.superlapp.auth.domain.entity;

import com.superlapp.auth.domain.entity.RoleEntity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_uuid", updatable = false, nullable = false)
    private UUID uuid;

    @PrePersist
    protected void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    @Column(name = "username", nullable = false, unique = true)
    private String name;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    //TODO: Fin this:
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<RoleEntity> userRoles = new ArrayList<>();

    public long getId() {
        return this.id;
    }

    public void setId(long newId) {
        this.id = newId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void getUuid(UUID newUuid) {
        this.uuid = newUuid;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }

    public List<RoleEntity> getRoles() {
        return this.userRoles;
    }

    public void setRoles(List<RoleEntity> newUserRoles) {
        this.userRoles = newUserRoles;
    }
}
