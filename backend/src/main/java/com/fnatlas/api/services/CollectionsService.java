package com.fnatlas.api.services;

import com.fnatlas.api.entities.Collection;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.exceptions.CollectionNotFoundException;
import com.fnatlas.api.repositories.CollectionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionsService {

    private final CollectionsRepository collectionsRepository;
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
                .orElseThrow(() -> new CollectionNotFoundException(id));
    }

    public Collection updateCollection(Long id, Collection collectionUpdates) {
        Collection collection = getCollectionById(id);

        if (collectionUpdates.getName() != null) collection.setName(collectionUpdates.getName());
        if (collectionUpdates.getDescription() != null) collection.setDescription(collectionUpdates.getDescription());

        return collectionsRepository.save(collection);
    }

    public void deleteCollection(Long id) {
        if (!collectionsRepository.existsById(id)) throw new CollectionNotFoundException(id);
        collectionsRepository.deleteById(id);
    }

//    public Collection addMapToCollection(Long id, String mapCode) {
//        Collection collection = getCollectionById(id);
//        collection.getMaps().add(mapCode);
//        return collectionsRepository.save(collection);
//    }
//
//    public Collection removeMapFromCollection(Long id, String mapCode) {
//        Collection collection = getCollectionById(id);
//        collection.getMaps().remove(mapCode);
//        return collectionsRepository.save(collection);
//    }
}
