package com.heelstrike.core;

import jakarta.persistence.*;

/**
 * TODO: Refactor getId, setId, getName, setName methods into non-abstract methods.
 * Currently, getId(), setId(), getName() etc... methods are abstract.
 * naturally, this causes issues when, for example, UserEntity class in auth-service needs
 * to return an id with the type of UUID, this breaks. Either use generics or just @MappedSuperClass
 * annotation to fix this.
 * */
@MappedSuperclass
public abstract class BaseEntity {

    public abstract int getId();

    public abstract void setId(int newId);

    public abstract String getName();

    public abstract void setName(String newName);

}