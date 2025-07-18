package com.fnatlas.api.services;

import com.fnatlas.api.dtos.MapMetricsResponse;
import com.fnatlas.api.dtos.MapResponse;
import com.fnatlas.api.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FortniteApiService {

    private final WebClient fortniteWebClient;

    public MapResponse getMapByCode(String mapCode) {
        if(!isValidMapCode(mapCode))
            throw new IllegalArgumentException("Invalid map code");

        try {
            return fortniteWebClient
                    .get()
                    .uri("/islands/{mapCode}", mapCode)
                    .retrieve()
                    .bodyToMono(MapResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new EntityNotFoundException("Fortnite Map", "map code", mapCode);
            else
                throw new RuntimeException("Error fetching map data: " + e.getMessage(), e);
        }
    }

    public MapMetricsResponse getMapMetrics(String mapCode, String interval, List<String>metrics, String from, String to) {
        if(!isValidMapCode(mapCode)) throw new IllegalArgumentException("Invalid map code");
        if(!isValidInterval(interval)) throw new IllegalArgumentException("Invalid interval. Valid values are: day, hour, minute");

        try {
            String url = String.format("/islands/%s/metrics/%s", mapCode, interval);

            return fortniteWebClient
                    .get()
                    .uri(uriBuilder -> {
                        var builder = uriBuilder.path(url);

                        if(metrics != null && !metrics.isEmpty()) builder = builder.queryParam("metrics", metrics.toArray());
                        if(from != null && !from.isEmpty()) builder = builder.queryParam("from", from);
                        if(to != null && !to.isEmpty()) builder = builder.queryParam("to", to);

                        return builder.build();
                    })
                    .retrieve()
                    .bodyToMono(MapMetricsResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new EntityNotFoundException("Fortnite Map", "map code", mapCode);
            else
                throw new RuntimeException("Error fetching map metrics: " + e.getMessage(), e);
        }
    }

    private boolean isValidInterval(String interval) {
        return Arrays.asList("day", "hour", "minute").contains(interval);
    }

    private boolean isValidMapCode(String mapCode) {
        return mapCode != null && !mapCode.trim().isEmpty() && mapCode.matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}$");
    }
}
