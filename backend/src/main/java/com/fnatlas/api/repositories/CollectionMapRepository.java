package com.fnatlas.api.repositories;

import com.fnatlas.api.entities.Collection;
import com.fnatlas.api.entities.CollectionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionMapRepository extends JpaRepository<CollectionMap, Integer> {
    List<CollectionMap> findCollectionMapsByCollection(Collection collectionId);
    void deleteByCollectionIdAndMapCode(Long collectionId, String mapCode);
    boolean existsByCollectionAndMapCode(Collection collectionId, String mapCode);
}
