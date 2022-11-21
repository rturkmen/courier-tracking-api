package com.migros.courier.service;

import com.migros.courier.exception.BusinessException;
import com.migros.courier.model.StoreModel;
import com.migros.courier.model.request.StoreUpdateRequest;
import com.migros.courier.persistence.entity.Store;
import com.migros.courier.persistence.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.migros.courier.constant.TestConstants.ATASEHIR_MIGROS;
import static com.migros.courier.util.Generator.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock
    StoreRepository storeRepository;
    @Mock
    MapperService mapper;

    @InjectMocks
    StoreService storeService;

    @Test
    void whenWantsToCreateNewStore_shouldSuccessfullyCreate() {
        var request = createStoreRequest();
        var store = createStore(ATASEHIR_MIGROS);
        var storeModel = createStoreModel(ATASEHIR_MIGROS);
        when(storeRepository.findStoreByNameAndLatAndLng(request.getName(), request.getLat(), request.getLng())).thenReturn(Optional.empty());
        when(mapper.map(request, Store.class)).thenReturn(store);
        when(storeRepository.save(store)).thenReturn(store);
        when(mapper.map(store, StoreModel.class)).thenReturn(storeModel);
        var result = storeService.createNewStore(request);
        assertEquals("Success", result.getMessage());
    }

    @Test
    void whenWantsToCreateNewStore_ifStoreIsExist_thenShouldThrowException() {
        var request = createStoreRequest();
        when(storeRepository.findStoreByNameAndLatAndLng(request.getName(), request.getLat(), request.getLng())).thenReturn(Optional.of(new Store()));
        assertThrows(BusinessException.class, () -> storeService.createNewStore(request));
    }

    @Test
    void getAllStoresTest() {
        var stores = createStoreList();
        when(storeRepository.findAll()).thenReturn(stores);
        var result = storeService.getAllStores();
        assertThat(result.size() > 0);
    }

    @Test
    void whenWantsToUpdateStore_ifStoreDoesntExist_thenShouldThrowException() {
        var request = new StoreUpdateRequest();
        when(storeRepository.findById(request.getStoreId())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> storeService.updateStore(request));
    }

    @Test
    void whenWantsToUpdateStore_shouldSuccessfullyUpdate() {
        var request = createStoreUpdateRequest();
        var store = createStore(ATASEHIR_MIGROS);
        var storeModel = createStoreModel(ATASEHIR_MIGROS);
        when(storeRepository.findById(request.getStoreId())).thenReturn(Optional.ofNullable(store));
        when(storeRepository.save(store)).thenReturn(store);
        when(mapper.map(store, StoreModel.class)).thenReturn(storeModel);
        var result = storeService.updateStore(request);
        assertEquals(storeModel.getLng(), request.getLng());
        assertEquals(storeModel.getLat(), request.getLat());

    }
}
