package com.fnatlas.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "collection_maps")
@NoArgsConstructor
@AllArgsConstructor
public class CollectionMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "map_code", nullable = false)
    private String mapCode;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @CreationTimestamp
    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;

    public CollectionMap(String mapCode, Collection collection) {
        this.mapCode = mapCode;
        this.collection = collection;
    }

}
