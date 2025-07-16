package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.CollectionMapRequest;
import com.fnatlas.api.entities.Collection;
import com.fnatlas.api.entities.CollectionMap;
import com.fnatlas.api.services.CollectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/collections")
public class CollectionsController {

    private final CollectionsService collectionsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection createCollection(@RequestBody Collection collection, @RequestParam Long userId) {
        return collectionsService.createCollection(collection, userId);
    }

    @GetMapping("/user/{userId}")
    public List<Collection> getCollectionsByUserId(@PathVariable Long userId) {
        return collectionsService.getCollectionsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Collection getCollectionById(@PathVariable Long id) {
        return collectionsService.getCollectionById(id);
    }

    @PutMapping("/{id}")
    public Collection updateCollection(@PathVariable Long id, @RequestBody Collection collectionUpdates) {
        return collectionsService.updateCollection(id, collectionUpdates);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCollection(@PathVariable Long id) {
        collectionsService.deleteCollection(id);
    }

    @PostMapping("/{id}/maps")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionMap addMapToCollection(@PathVariable Long id, @RequestBody CollectionMapRequest collectionMapRequest) {
        return collectionsService.addMapToCollection(id, collectionMapRequest.getMapCode());
    }

    @GetMapping("/{id}/maps")
    public List<CollectionMap> getMapsInCollection(@PathVariable Long id) {
        return collectionsService.getMapsByCollectionId(id);
    }

    @DeleteMapping("/{id}/maps/{mapCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMapFromCollection(@PathVariable Long id, @PathVariable String mapCode) {
        collectionsService.removeMapFromCollection(id, mapCode);
    }


}
