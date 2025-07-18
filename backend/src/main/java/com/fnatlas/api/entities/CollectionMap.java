package com.fnatlas.api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "collection_maps")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "map_code", nullable = false)
    @NotBlank(message = "Map code cannot be blank")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}$", message = "Map code must be in format XXXX-XXXX-XXXX")
    private String mapCode;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    @NotNull(message = "Collection cannot be null")
    private Collection collection;

    @CreationTimestamp
    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
