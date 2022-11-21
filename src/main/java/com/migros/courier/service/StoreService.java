package com.migros.courier.service;

import com.migros.courier.exception.BusinessException;
import com.migros.courier.model.StoreModel;
import com.migros.courier.model.request.StoreRequest;
import com.migros.courier.model.request.StoreUpdateRequest;
import com.migros.courier.model.response.BaseResponse;
import com.migros.courier.persistence.entity.Store;
import com.migros.courier.persistence.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.migros.courier.model.enums.ErrorEnum.STORE_IS_EXIST;
import static com.migros.courier.model.enums.ErrorEnum.STORE_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MapperService mapper;

    @CacheEvict(value = "stores", cacheManager = "cacheManager", allEntries = true)
    public BaseResponse createNewStore(StoreRequest request) {
        log.info("Store creation started with this store's name: {}", request.getName());
        final var optionalStore = storeRepository.findStoreByNameAndLatAndLng(request.getName(), request.getLat(), request.getLng());
        if (optionalStore.isPresent())
            throw new BusinessException(STORE_IS_EXIST);
        final var store = mapper.map(request, Store.class);
        storeRepository.save(store);
        log.info("Store creation ended with this store's name : {}", request.getName());
        return new BaseResponse(mapper.map(store, StoreModel.class));
    }

    @Cacheable(value = "stores", cacheManager = "cacheManager")
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @CacheEvict(value = "stores", cacheManager = "cacheManager", allEntries = true)
    public BaseResponse updateStore(StoreUpdateRequest request) {
        log.info("Store's database updated with store id : {}", request.getStoreId());
        final var optionalStore = storeRepository.findById(request.getStoreId());
        if (optionalStore.isEmpty())
            throw new BusinessException(STORE_NOT_FOUND);
        var updatedStore = storeRepository.save(updateStoreEntity(request, optionalStore.get()));
        log.info("Store's update service ended with store id: {}", request.getStoreId());
        return new BaseResponse(mapper.map(updatedStore, StoreModel.class));
    }

    private Store updateStoreEntity(StoreUpdateRequest request, Store store) {
        store.setLat(request.getLat());
        store.setLng(request.getLng());
        return store;
    }

}
