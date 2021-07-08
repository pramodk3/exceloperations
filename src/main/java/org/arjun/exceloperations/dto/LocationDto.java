package org.arjun.exceloperations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    @JsonProperty("type")
    private String type;

    @JsonProperty("coordinates")
    private List<Double> coordinates;

}
