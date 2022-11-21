package com.migros.courier.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierUpdateRequest {

    @NotNull
    private Long identityNumber;
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;
    @NotNull
    private LocalDateTime time;

}
