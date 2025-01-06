package com.superlapp.auth.domain.entity;

import com.superlapp.core.BaseEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    //@Column(name = "firstname")
    //private String firstname;

    //@Column(name = "middlenames")
    //private Json middlenames;

    // @Column(name = "surname")
    // private String surname;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    //TODO: Fin this:
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<RoleEntity> userRoles = new ArrayList<>();

    public UUID getUuid() {
        return this.uuid;
    }

    public void getUuid(UUID newUuid) {
        this.uuid = newUuid;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int newId) {
        this.id = newId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
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
