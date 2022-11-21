package com.migros.courier.service;

import com.migros.courier.model.StoreModel;
import com.migros.courier.persistence.entity.Courier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.migros.courier.constant.TestConstants.ATASEHIR_MIGROS;
import static com.migros.courier.constant.TestConstants.NOVADA_MIGROS;
import static com.migros.courier.util.Generator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MapperServiceTest {

    @InjectMocks
    private MapperService mapperService;

    @Test
    void mapValuesSuccessfully() {
        var courierRequest = createCourierRequest();
        var courierEntity = createCourierEntity();
        var result = mapperService.map(courierRequest, Courier.class);
        assertEquals(courierEntity.getIdentityNumber(), result.getIdentityNumber());
    }

    @Test
    void mapListValuesSuccessfully() {
        var storeList = createStoreList();
        var expected = List.of(createStoreModel(ATASEHIR_MIGROS), createStoreModel(NOVADA_MIGROS));
        var result = mapperService.mapList(storeList, StoreModel.class);
        assertEquals(expected, result);
    }

}
