package com.migros.courier.service;

import com.migros.courier.exception.BusinessException;
import com.migros.courier.model.CourierModel;
import com.migros.courier.model.CourierMovesModel;
import com.migros.courier.model.request.CourierRequest;
import com.migros.courier.model.request.CourierUpdateRequest;
import com.migros.courier.model.response.BaseResponse;
import com.migros.courier.persistence.entity.Courier;
import com.migros.courier.persistence.entity.CourierMoves;
import com.migros.courier.persistence.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.migros.courier.model.enums.ErrorEnum.COURIER_IS_EXIST;
import static com.migros.courier.model.enums.ErrorEnum.COURIER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;
    private final CourierMovesService courierMovesService;
    private final MapperService mapper;

    public BaseResponse createNewCourier(CourierRequest request) {
        log.info("Courier creation service started for identity number : {}", request.getIdentityNumber());
        final var optionalCourierEntity = courierRepository.findByIdentityNumber(request.getIdentityNumber());
        if (optionalCourierEntity.isPresent())
            throw new BusinessException(COURIER_IS_EXIST);
        final var courier = mapper.map(request, Courier.class);
        var courierEntity = courierRepository.save(courier);
        var courierMoves = courierMovesService.processCourierMoves(courierEntity, true);
        log.info("Courier created for identity number :{}", courierEntity.getIdentityNumber());
        return new BaseResponse(convertModel(courierMoves, courierEntity));
    }

    public BaseResponse updateCourier(CourierUpdateRequest request) {
        log.info("Courier updated service started for courier id : {}", request.getIdentityNumber());
        var optionalCourier = courierRepository.findByIdentityNumber(request.getIdentityNumber());
        if (optionalCourier.isEmpty())
            throw new BusinessException(COURIER_NOT_FOUND);
        final var courier = optionalCourier.get();
        courier.setLat(request.getLat());
        courier.setLng(request.getLng());
        courier.setTime(request.getTime());
        var courierEntity = courierRepository.save(courier);
        var courierMoves = courierMovesService.processCourierMoves(courierEntity, false);
        log.info("Courier updated service ended for courier id : {}", courierEntity.getIdentityNumber());
        return new BaseResponse(convertModel(courierMoves, courierEntity));
    }


    private CourierModel convertModel(List<CourierMoves> courierMoves, Courier courier) {
        return CourierModel.builder()
                .lat(courier.getLat())
                .lng(courier.getLng())
                .courierMoves(mapper.mapList(courierMoves, CourierMovesModel.class))
                .time(courier.getTime())
                .identityNumber(courier.getIdentityNumber())
                .build();
    }

}
