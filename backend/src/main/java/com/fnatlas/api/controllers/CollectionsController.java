package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.collection.CollectionCreateRequest;
import com.fnatlas.api.dtos.collection.CollectionMapRequest;
import com.fnatlas.api.dtos.collection.CollectionUpdateRequest;
import com.fnatlas.api.entities.Collection;
import com.fnatlas.api.entities.CollectionMap;
import com.fnatlas.api.services.AuthService;
import com.fnatlas.api.services.CollectionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/collections")
public class CollectionsController {

    private final CollectionsService collectionsService;
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection createCollection(@PathVariable Long userId, @RequestBody @Valid CollectionCreateRequest request, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return collectionsService.createCollection(request, userId);
    }

    @GetMapping
    public List<Collection> getCollectionsByUserId(@PathVariable Long userId, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return collectionsService.getCollectionsByUserId(userId);
    }

    @GetMapping("/{collectionId}")
    public Collection getCollection(@PathVariable Long userId, @PathVariable Long collectionId, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return collectionsService.getCollectionByIdAndUserId(collectionId, userId);
    }

    @PutMapping("/{collectionId}")
    public Collection updateCollection(@PathVariable Long userId, @PathVariable Long collectionId, @RequestBody @Valid CollectionUpdateRequest request, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return collectionsService.updateCollection(collectionId, userId, request);
    }

    @DeleteMapping("/{collectionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCollection(@PathVariable Long userId, @PathVariable Long collectionId, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        collectionsService.deleteCollection(collectionId, userId);
    }

    // Map-related endpoints

    @PostMapping("/{collectionId}/maps")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionMap addMapToCollection(@PathVariable Long userId, @PathVariable Long collectionId, @RequestBody @Valid CollectionMapRequest request, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return collectionsService.addMapToCollection(collectionId, userId, request.getMapCode());
    }

    @GetMapping("/{collectionId}/maps")
    public List<CollectionMap> getMapsInCollection(@PathVariable Long userId, @PathVariable Long collectionId, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return collectionsService.getMapsByCollectionIdAndUserId(collectionId, userId);
    }

    @DeleteMapping("/{collectionId}/maps/{mapCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMapFromCollection(@PathVariable Long userId, @PathVariable Long collectionId, @PathVariable String mapCode, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        collectionsService.removeMapFromCollection(collectionId, userId, mapCode);
    }


}
