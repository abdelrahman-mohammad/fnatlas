package com.fnatlas.api.repositories;

import com.fnatlas.api.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionsRepository extends JpaRepository<Collection, Long> {
    List<Collection> getCollectionsByUserId(Long userId);
}
