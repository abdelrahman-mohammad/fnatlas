package com.fnatlas.api.services;

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

    public Collection createCollection(Collection collection, Long userId) {
        User user = userService.getUserById(userId);
        collection.setUser(user);
        return collectionsRepository.save(collection);
    }

    public List<Collection> getCollectionsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return collectionsRepository.getCollectionsByUserId(user.getId());
    }

    public Collection getCollectionById(Long id) {
        return collectionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collection", id));
    }

    public Collection updateCollection(Long id, Collection collectionUpdates) {
        Collection collection = getCollectionById(id);

        if (collectionUpdates.getName() != null) collection.setName(collectionUpdates.getName());
        if (collectionUpdates.getDescription() != null) collection.setDescription(collectionUpdates.getDescription());

        return collectionsRepository.save(collection);
    }

    public void deleteCollection(Long id) {
        if (!collectionsRepository.existsById(id)) throw new EntityNotFoundException("Collection", id);
        collectionsRepository.deleteById(id);
    }

    public CollectionMap addMapToCollection(Long id, String mapCode) {
        Collection collection = getCollectionById(id);

        if(collectionMapRepository.existsByCollectionIdAndMapCode(id, mapCode))
            throw new IllegalArgumentException("Map already exists in the collection");

        return collectionMapRepository.save(new CollectionMap(mapCode, collection));
    }

    public List<CollectionMap> getMapsByCollectionId(Long collectionId) {
        return collectionMapRepository.findByCollectionId(collectionId);
    }

    @Transactional
    public void removeMapFromCollection(Long id, String mapCode) {
        if (!collectionsRepository.existsById(id))
            throw new EntityNotFoundException("Collection", id);
        if (!collectionMapRepository.existsByCollectionIdAndMapCode(id, mapCode))
            throw new IllegalArgumentException("Map not found in the collection");

        collectionMapRepository.deleteByCollectionIdAndMapCode(id, mapCode);
    }

}
