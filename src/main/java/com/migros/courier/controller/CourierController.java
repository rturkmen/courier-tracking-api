package com.migros.courier.controller;

import com.migros.courier.model.request.CourierRequest;
import com.migros.courier.model.request.CourierUpdateRequest;
import com.migros.courier.model.response.BaseResponse;
import com.migros.courier.service.CourierMovesService;
import com.migros.courier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.migros.courier.model.constants.UrlConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COURIER)
public class CourierController {

    private final CourierService service;
    private final CourierMovesService courierMovesService;

    @PostMapping(CREATE)
    BaseResponse createNewCourier(@Valid @RequestBody CourierRequest request) {
        return service.createNewCourier(request);
    }

    @PutMapping(UPDATE)
    BaseResponse updateCourier(@Valid @RequestBody CourierUpdateRequest request) {
        return service.updateCourier(request);
    }

    @GetMapping(DETAILS)
    BaseResponse courierMovesDetails(@RequestParam(value = "courier_id") Long courierId) {
        return courierMovesService.courierMovesDetails(courierId);
    }
}
