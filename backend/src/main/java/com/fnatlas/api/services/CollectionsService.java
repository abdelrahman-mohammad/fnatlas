package com.fnatlas.api.services;

import com.fnatlas.api.dtos.collection.CollectionCreateRequest;
import com.fnatlas.api.dtos.collection.CollectionUpdateRequest;
import com.fnatlas.api.entities.Collection;
import com.fnatlas.api.entities.CollectionMap;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.exceptions.EntityNotFoundException;
import com.fnatlas.api.repositories.CollectionMapRepository;
import com.fnatlas.api.repositories.CollectionsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionsService {

    private final CollectionsRepository collectionsRepository;
    private final CollectionMapRepository collectionMapRepository;
    private final UserService userService;

    public Collection createCollection(CollectionCreateRequest collectionCreateRequest, Long userId) {
        User user = userService.getUserById(userId);

        Collection collection = Collection.builder()
                .name(collectionCreateRequest.getName())
                .description(collectionCreateRequest.getDescription())
                .user(user)
                .build();

        return collectionsRepository.save(collection);
    }

    public List<Collection> getCollectionsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return collectionsRepository.findCollectionsByUserId(user.getId());
    }

    public Collection getCollectionByIdAndUserId(Long collectionId, Long userId) {
        userService.getUserById(userId);
        return collectionsRepository.findCollectionByIdAndUserId(collectionId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Collection", collectionId));
    }

    public Collection updateCollection(Long collectionId, Long userId, CollectionUpdateRequest collectionUpdates) {
        Collection collection = getCollectionByIdAndUserId(collectionId, userId);

        if (collectionUpdates.getName() != null) collection.setName(collectionUpdates.getName());
        if (collectionUpdates.getDescription() != null) collection.setDescription(collectionUpdates.getDescription());

        return collectionsRepository.save(collection);
    }

    public void deleteCollection(Long collectionId, Long userId) {
        Collection collection = getCollectionByIdAndUserId(collectionId, userId);
        collectionsRepository.delete(collection);
    }

    public CollectionMap addMapToCollection(Long collectionId, Long userId, String mapCode) {
        Collection collection = getCollectionByIdAndUserId(collectionId, userId);

        if(collectionMapRepository.existsByCollectionAndMapCode(collection, mapCode))
            throw new IllegalArgumentException("Map already exists in the collection");

        CollectionMap newCollectionMap = CollectionMap.builder().mapCode(mapCode).collection(collection).build();
        return collectionMapRepository.save(newCollectionMap);
    }

    public List<CollectionMap> getMapsByCollectionIdAndUserId(Long collectionId, Long userId) {
        Collection collection = getCollectionByIdAndUserId(collectionId, userId);
        return collectionMapRepository.findCollectionMapsByCollection(collection);
    }

    @Transactional
    public void removeMapFromCollection(Long collectionId, Long userId, String mapCode) {
        Collection collection = getCollectionByIdAndUserId(collectionId, userId);
        if (!collectionMapRepository.existsByCollectionAndMapCode(collection, mapCode))
            throw new EntityNotFoundException("Map", "map code", mapCode);

        collectionMapRepository.deleteByCollectionIdAndMapCode(collectionId, mapCode);
    }

}
