package com.migros.courier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierTotalDistanceModel {

    private Long courierId;
    private Double totalDistance;
    private List<CourierMovesModel> courierMoves;
}
