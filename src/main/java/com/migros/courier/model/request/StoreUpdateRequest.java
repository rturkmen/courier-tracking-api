package com.migros.courier.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreUpdateRequest {

    @NotNull
    private Long storeId;
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;

}
