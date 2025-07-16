package com.fnatlas.api.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityType, String message) {
        super(message);
    }

    public EntityNotFoundException(String entityType, Long id) {
        super(entityType + " not found with id: " + id);
    }

    public EntityNotFoundException(String entityType, String field, String value) {
        super(entityType + " not found with " + field + ": " + value);
    }
}
