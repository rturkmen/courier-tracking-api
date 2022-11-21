package com.migros.courier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreModel {
    private Long id;
    private String name;
    private Double lat;
    private Double lng;
}
