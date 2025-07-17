package com.fnatlas.api.services;

import com.fnatlas.api.dtos.MapResponse;
import com.fnatlas.api.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class FortniteApiService {

    private final WebClient fortniteWebClient;

    public MapResponse getMapByCode(String mapCode) {
        if(!isValidMapCode(mapCode))
            throw new IllegalArgumentException("Invalid map code");

        try {
            MapResponse mapResponse = fortniteWebClient
                    .get()
                    .uri("/islands/{mapCode}", mapCode)
                    .retrieve()
                    .bodyToMono(MapResponse.class)
                    .block();

            if (mapResponse != null) {
                return MapResponse.builder()
                        .mapCode(mapResponse.getMapCode())
                        .creatorCode(mapResponse.getCreatorCode())
                        .displayName(mapResponse.getDisplayName())
                        .title(mapResponse.getTitle())
                        .category(mapResponse.getCategory())
                        .createdIn(mapResponse.getCreatedIn())
                        .tags(mapResponse.getTags())
                        .exists(true)
                        .build();
            } else return MapResponse.builder().mapCode(mapCode).exists(false).build();

        } catch (WebClientResponseException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new EntityNotFoundException("Fortnite Map", "map code", mapCode);
            else
                throw new RuntimeException("Error fetching map data: " + e.getMessage(), e);

        }
    }

    public boolean mapExists(String mapCode) {
        try {
            MapResponse mapResponse = getMapByCode(mapCode);
            return mapResponse.isExists();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidMapCode(String mapCode) {
        return mapCode != null && !mapCode.trim().isEmpty() && mapCode.matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}$");
    }

}
