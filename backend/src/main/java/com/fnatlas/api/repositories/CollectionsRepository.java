package com.fnatlas.api.repositories;

import com.fnatlas.api.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionsRepository extends JpaRepository<Collection, Long> {
    List<Collection> findCollectionsByUserId(Long userId);
    Optional<Collection> findCollectionByIdAndUserId(Long collectionId, Long userId);
}
