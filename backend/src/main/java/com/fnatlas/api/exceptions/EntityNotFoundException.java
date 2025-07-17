package com.fnatlas.api.exceptions;

public class EntityNotFoundException extends jakarta.persistence.EntityNotFoundException {
    public EntityNotFoundException(String entityType, Long id) {
        super(entityType + " not found with id: " + id);
    }

    public EntityNotFoundException(String entityType, String field, Object value) {
        super(entityType + " not found with " + field + ": " + value);
    }
}
