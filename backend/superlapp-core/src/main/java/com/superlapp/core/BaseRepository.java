package com.superlapp;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseRepository<T> {
    //C.R.U.D Create, Read, Update, Delete
    @Inject
    protected EntityManager entityManager;

    public BaseRepository(){}

    protected T findEntityById(Class<T> entityClass, int id) {
        return entityManager.find(entityClass, id);
    }

    protected T findEntityByNonNumericId(Class<T> entityClass, T id) {
        return entityManager.find(entityClass, id);
    }

}
