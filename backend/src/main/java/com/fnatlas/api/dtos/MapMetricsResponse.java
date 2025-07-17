package com.fnatlas.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapMetricsResponse {
    private List<DataPoint> averageMinutesPerPlayer;
    private List<DataPoint> peakCCU;
    private List<DataPoint> favorites;
    private List<DataPoint> minutesPlayed;
    private List<DataPoint> recommendations;
    private List<DataPoint> plays;
    private List<DataPoint> uniquePlayers;
    private List<RetentionPoint> retention;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataPoint {
        private Long value;
        private String timestamp;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RetentionPoint {
        @JsonProperty("d1")
        private Long dayOneRetention;
        @JsonProperty("d7")
        private Long daySevenRetention;
        private String timestamp;
    }
}
