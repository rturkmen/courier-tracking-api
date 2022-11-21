package com.migros.courier.service;

import com.migros.courier.exception.BusinessException;
import com.migros.courier.model.CourierMovesModel;
import com.migros.courier.model.request.CourierUpdateRequest;
import com.migros.courier.model.response.BaseResponse;
import com.migros.courier.persistence.entity.Courier;
import com.migros.courier.persistence.entity.CourierMoves;
import com.migros.courier.persistence.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.migros.courier.util.Generator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourierServiceTest {

    @InjectMocks
    CourierService courierService;
    @Mock
    CourierRepository courierRepository;
    @Mock
    CourierMovesService courierMovesService;
    @Mock
    MapperService mapperService;

    @Test
    void whenNewCourierComes_shouldSuccessfullyCreate() {
        var request = createCourierRequest();
        var courierEntity = createCourierEntity();
        var courierModel = createCourierModel();
        var expectedResponse = new BaseResponse(courierModel);

        when(courierRepository.findByIdentityNumber(request.getIdentityNumber())).thenReturn(Optional.empty());
        when(mapperService.map(request, Courier.class)).thenReturn(courierEntity);
        when(courierRepository.save(courierEntity)).thenReturn(courierEntity);
        when(courierMovesService.processCourierMoves(courierEntity, true)).thenReturn(List.of(new CourierMoves()));
        var result = courierService.createNewCourier(request);
        assertEquals(expectedResponse, result);
    }

    @Test
    void whenCourierIsExistWhenCallCreateService_shouldThrowException() {
        var request = createCourierRequest();
        var courierEntity = createCourierEntity();
        when(courierRepository.findByIdentityNumber(request.getIdentityNumber())).thenReturn(Optional.ofNullable(courierEntity));
        assertThrows(BusinessException.class, () -> courierService.createNewCourier(request));
    }

    @Test
    void whenExistingCourierComes_shouldSuccessfullyUpdate() {
        var request = createCourierUpdateRequest();
        var courierEntity = createCourierEntity();
        var courierModel = createCourierModel();
        var courierMove = List.of(new CourierMoves());
        var courierMovesModel = List.of(new CourierMovesModel());
        courierModel.setCourierMoves(courierMovesModel);
        var expectedResponse = new BaseResponse(courierModel);

        when(courierRepository.findByIdentityNumber(request.getIdentityNumber())).thenReturn(Optional.ofNullable(courierEntity));
        when(courierRepository.save(courierEntity)).thenReturn(courierEntity);
        when(courierMovesService.processCourierMoves(courierEntity, false)).thenReturn(courierMove);
        when(mapperService.mapList(courierMove, CourierMovesModel.class)).thenReturn(courierMovesModel);
        var result = courierService.updateCourier(request);
        assertEquals(expectedResponse, result);
    }


    @Test
    void whenCourierNotFoundWhenCallUpdateService_shouldThrowException() {
        var request = new CourierUpdateRequest();
        when(courierRepository.findByIdentityNumber(request.getIdentityNumber())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> courierService.updateCourier(request));
    }


}
