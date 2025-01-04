package com.superlapp.auth.domain.entity;

import com.superlapp.core.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "role_name")
    private String name;

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
}
