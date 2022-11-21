package com.migros.courier.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourierMovesModel {

    private Long courierId;
    private StoreModel store;
    private Double lng;
    private Double lat;
    private LocalDateTime allowedTime;
    private Double distance;
}
