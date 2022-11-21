package com.migros.courier.controller;

import com.migros.courier.model.request.StoreRequest;
import com.migros.courier.model.request.StoreUpdateRequest;
import com.migros.courier.model.response.BaseResponse;
import com.migros.courier.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.migros.courier.model.constants.UrlConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(STORE)
public class StoreController {

    private final StoreService service;

    @PostMapping(CREATE)
    BaseResponse createNewStore(@Valid @RequestBody StoreRequest request) {

        return service.createNewStore(request);
    }

    @PutMapping(UPDATE)
    BaseResponse updateStore(@Valid @RequestBody StoreUpdateRequest request) {
        return service.updateStore(request);
    }

}
