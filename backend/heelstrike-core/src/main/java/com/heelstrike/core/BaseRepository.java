package com.heelstrike.core;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.*;

public abstract class BaseRepository<T> {
    //C.R.U.D Create, Read, Update, Delete
    @Inject
    protected EntityManager entityManager;
    //private Class<T> entityClass;
    //private T entity;

    public BaseRepository(){
    }

    public T saveEntity(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    protected T updateEntity(T entity) {
        return entityManager.merge(entity);
    }

    protected T findEntityById(Class<T> entityClass, int id) {
        return entityManager.find(entityClass, id);
    }

    public T findEntityByUuid(Class<T> entityClass, UUID uuid) {
        return entityManager.find(entityClass, uuid);
    }

    public List<T> findAllEntities(Class<T> entityClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public <V> Optional<T> findSingleEntityByField(Class<T> entityClass, String field, V value) throws NoResultException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(field), value));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<List<T>> findEntity(Class<T> entityClass, Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            String fieldName = filter.getKey();
            Object fieldValue = filter.getValue();

            if (fieldValue != null) {
                predicates.add(criteriaBuilder.equal(root.get(fieldName), fieldValue));
            }
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));

        List<T> results = entityManager.createQuery(query).getResultList();

        return Optional.ofNullable(results.isEmpty() ? null : results);
    }

    protected <V> List<Optional<T>> findEntityByField(Class<T> entityClass, String field, V value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(field), value));

        List<T> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return resultList.stream()
                .map(Optional::ofNullable)
                .toList();
    }

    //TODO: Refactor, shouldn't even be returning list of optionals (see above) but didnt have time to refactor whole recipe service
    protected <V> List<T> findEntityByField2(Class<T> entityClass, String field, V value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(field), value));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    protected <V> int deleteByField(Class<T> entityClass, String field, V value) throws NoResultException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(entityClass);
        Root<T> root = criteriaDelete.from(entityClass);

        criteriaDelete.where(criteriaBuilder.equal(root.get(field), value));

        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}

