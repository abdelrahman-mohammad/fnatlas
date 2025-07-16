package com.fnatlas.api.exceptions;

public class CollectionNotFoundException extends RuntimeException {
    public CollectionNotFoundException(String message) {
      super(message);
    }

    public CollectionNotFoundException(Long collectionId) {
      super("Collection not found with id: " + collectionId);
    }

    public CollectionNotFoundException(String field, String value) {
      super("Collection not found with " + field + ": " + value);
    }
}
