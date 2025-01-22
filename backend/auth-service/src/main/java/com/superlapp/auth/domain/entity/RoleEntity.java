package com.superlapp.auth.domain.entity;

import com.superlapp.core.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    //@OneToMany(mappedBy = "role")
    //private List<UserEntity> users = new ArrayList<>();

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
}