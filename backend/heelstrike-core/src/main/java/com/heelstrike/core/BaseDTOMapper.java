package com.heelstrike.core;

import java.util.List;

public class BaseDTOMapper {

    public <T, D> List<T> mapToEntity( Class<T> entityClass, List<D> DTOs, BaseRepository<T> repository, String entityName) {
        if (DTOs == null || DTOs.isEmpty()) {
            return List.of();
        }

        return DTOs.stream()
                .map(DTO -> {
                    if (!(DTO instanceof BaseDTO baseDTO)) {
                        throw new IllegalArgumentException("Invalid DTO type for " + entityName);
                    }
                    try {
                        return repository.findEntityById(entityClass, baseDTO.getId());
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid " + entityName + " ID: " + baseDTO.getId(), e);
                    }
                })
                .toList();
    }
}
