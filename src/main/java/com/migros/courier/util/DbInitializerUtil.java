package com.migros.courier.util;

import com.migros.courier.persistence.entity.Store;
import com.migros.courier.persistence.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbInitializerUtil {

    private final StoreRepository storeRepository;

    @PostConstruct
    void initializeDb() {
        createStoreInstances();
    }

    private void createStoreInstances() {
        var storeList = List.of(createStoreEntity(40.9923307, 29.1244229, "Ataşehir MMM Migros"),
                createStoreEntity(40.986106, 29.1161293, "Novada MMM Migros"),
                createStoreEntity(41.0066851, 28.6552262, "Beylikdüzü 5M Migros"),
                createStoreEntity(41.055783, 29.0210292, "Ortaköy MMM Migros"),
                createStoreEntity(40.9632463, 29.0630908, "Caddebostan MMM Migros"));
        storeRepository.saveAll(storeList);
    }


    private Store createStoreEntity(Double lat, Double lng, String name) {
        return Store.builder()
                .lat(lat)
                .lng(lng)
                .name(name)
                .build();
    }
}
