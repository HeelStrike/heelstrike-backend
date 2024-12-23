package com.superlapp.core;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    public abstract int getId();

    public abstract void setId(int newId);

    public abstract String getName();

    public abstract void setName(String newName);

}