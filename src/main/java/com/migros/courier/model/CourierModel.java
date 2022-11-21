package com.migros.courier.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourierModel {

    @JsonProperty("id")
    private Long identityNumber;
    private Double lat;
    private Double lng;
    private LocalDateTime time;
    private List<CourierMovesModel> courierMoves;

}
