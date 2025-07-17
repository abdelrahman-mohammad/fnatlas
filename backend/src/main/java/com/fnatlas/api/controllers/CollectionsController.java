package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.CollectionMapRequest;
import com.fnatlas.api.dtos.CollectionRequest;
import com.fnatlas.api.entities.Collection;
import com.fnatlas.api.entities.CollectionMap;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection createCollection(@PathVariable Long userId, @RequestBody @Valid CollectionRequest collectionRequest) {
        return collectionsService.createCollection(collectionRequest, userId);
    }

    @GetMapping
    public List<Collection> getCollectionsByUserId(@PathVariable Long userId) {
        return collectionsService.getCollectionsByUserId(userId);
    }

    @GetMapping("/{collectionId}")
    public Collection getCollection(@PathVariable Long userId, @PathVariable Long collectionId) {
        return collectionsService.getCollectionByIdAndUserId(collectionId, userId);
    }

    @PutMapping("/{collectionId}")
    public Collection updateCollection(@PathVariable Long userId, @PathVariable Long collectionId, @RequestBody @Valid CollectionRequest collectionRequest) {
        return collectionsService.updateCollection(collectionId, userId, collectionRequest);
    }

    @DeleteMapping("/{collectionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCollection(@PathVariable Long userId, @PathVariable Long collectionId) {
        collectionsService.deleteCollection(collectionId, userId);
    }

    // Map-related endpoints

    @PostMapping("/{collectionId}/maps")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionMap addMapToCollection(@PathVariable Long userId, @PathVariable Long collectionId, @RequestBody @Valid CollectionMapRequest collectionMapRequest) {
        return collectionsService.addMapToCollection(collectionId, userId, collectionMapRequest.getMapCode());
    }

    @GetMapping("/{collectionId}/maps")
    public List<CollectionMap> getMapsInCollection(@PathVariable Long userId, @PathVariable Long collectionId) {
        return collectionsService.getMapsByCollectionIdAndUserId(collectionId, userId);
    }

    @DeleteMapping("/{collectionId}/maps/{mapCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMapFromCollection(@PathVariable Long userId, @PathVariable Long collectionId, @PathVariable String mapCode) {
        collectionsService.removeMapFromCollection(collectionId, userId, mapCode);
    }


}
