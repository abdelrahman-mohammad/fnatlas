package com.fnatlas.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapResponse {
    @JsonProperty("code")
    private String mapCode;
    private String creatorCode;
    private String displayName;
    private String title;
    private String category;
    private String createdIn;
    private List<String> tags;
    private boolean exists = true;
}
