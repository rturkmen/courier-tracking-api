package com.migros.courier.service;

import com.migros.courier.exception.BusinessException;
import com.migros.courier.persistence.repository.CourierMovesRepository;
import com.migros.courier.persistence.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static com.migros.courier.constant.TestConstants.COURIER_ID;
import static com.migros.courier.util.Generator.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourierMovesServiceTest {

    @InjectMocks
    CourierMovesService service;
    @Mock
    CourierMovesRepository courierMovesRepository;

    @Mock
    CourierRepository courierRepository;

    @Mock
    MapperService mapper;
    @Mock
    StoreService storeService;

    @Test
    void whenCourierConditionsAreAvailableForLogging_itShouldBeLogSuccessfully() {
        var courier = createCourierForMoves();
        var courierMoves = createCourierMoves();
        var storeList = createStoreList();

        when(storeService.getAllStores()).thenReturn(storeList);
        when(courierMovesRepository.save(any())).thenReturn(courierMoves);
        var expected = List.of(courierMoves, courierMoves);
        var result = service.processCourierMoves(courier, true);

        assertEquals(expected, result);
    }

    @Test
    void whenCourierConditionsAreNotAvailableForLogging_shouldNotBeLogging() {
        var courier = createCourierForMoves();
        var storeList = createStoreList();
        when(storeService.getAllStores()).thenReturn(storeList);
        service.processCourierMoves(courier, true);
        assertThat(CollectionUtils.isEmpty(courier.getCourierMoves()));
    }

    @Test
    void couriersDistanceCalculateTest() {
        var courierEntity = createCourierEntity();
        var courierMoves = createCourierMoves();
        courierEntity.setCourierMoves(List.of(courierMoves));
        var totalDistanceModel = createCourierTotalDistanceModel();
        when(courierRepository.findByIdentityNumber(COURIER_ID)).thenReturn(Optional.ofNullable(courierEntity));
        var response = service.courierMovesDetails(courierEntity.getIdentityNumber());
        assertEquals(totalDistanceModel, response.getData());
    }

    @Test
    void whenCourierNotFoundWhenCallTotalDistanceService_shouldThrowException() {
        when(courierRepository.findByIdentityNumber(COURIER_ID)).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> service.courierMovesDetails(COURIER_ID));
    }
}
