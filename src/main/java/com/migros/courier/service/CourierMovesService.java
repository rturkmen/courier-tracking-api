package com.migros.courier.service;

import com.migros.courier.exception.BusinessException;
import com.migros.courier.model.CourierMovesModel;
import com.migros.courier.model.CourierTotalDistanceModel;
import com.migros.courier.model.response.BaseResponse;
import com.migros.courier.persistence.entity.Courier;
import com.migros.courier.persistence.entity.CourierMoves;
import com.migros.courier.persistence.entity.Store;
import com.migros.courier.persistence.repository.CourierMovesRepository;
import com.migros.courier.persistence.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.migros.courier.model.constants.ApiConstants.ALLOWED_ZONE;
import static com.migros.courier.model.enums.ErrorEnum.COURIER_NOT_FOUND;
import static com.migros.courier.util.ApiUtil.calculateDistance;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourierMovesService {

    private final CourierMovesRepository repository;

    private final CourierRepository courierRepository;
    private final MapperService mapper;
    private final StoreService storeService;

    public List<CourierMoves> processCourierMoves(Courier courier, boolean isCourierNew) {
        log.info("Courier moves process started for courier : {}", courier.getIdentityNumber());
        final var stores = storeService.getAllStores();
        var courierMoves = new ArrayList<CourierMoves>();
        stores.stream().filter(distanceIsAllowed(courier))
                .forEach(store -> {
                    if (isCourierNew) {
                        log.info("Courier has seen near store : {} ", store.getName());
                        var moves = saveCourierMoves(courier, store);
                        courierMoves.add(moves);
                    } else {
                        var isCourierMovesAllowed = repository.findByCourierAndStore(courier, store)
                                .stream().filter(moves -> moves.getAllowedTime().isAfter(courier.getTime())).collect(Collectors.toList()).size() > 0;
                        if (isCourierMovesAllowed) {
                            log.info("Courier has logged before on store : {} ", store.getName());
                        } else {
                            log.info("Courier has seen near store : {} ", store.getName());
                            var moves = saveCourierMoves(courier, store);
                            courierMoves.add(moves);
                        }
                    }
                });
        log.info("Courier moves process ended for courier : {}", courier.getIdentityNumber());
        return courierMoves;
    }

    public BaseResponse courierMovesDetails(Long courierId) {
        final var optionalCourier = courierRepository.findByIdentityNumber(courierId);
        if (optionalCourier.isEmpty())
            throw new BusinessException(COURIER_NOT_FOUND);
        final var totalDistance = optionalCourier.get().getCourierMoves().stream().mapToDouble(CourierMoves::getDistance).sum();
        return new BaseResponse(CourierTotalDistanceModel.builder()
                .totalDistance(totalDistance)
                .courierId(optionalCourier.get().getIdentityNumber())
                .courierMoves(mapper.mapList(optionalCourier.get().getCourierMoves(), CourierMovesModel.class)).build());
    }

    private Predicate<Store> distanceIsAllowed(Courier courier) {
        return store ->
                calculateDistance(courier.getLat(), courier.getLng(), store.getLat(), store.getLng()) <= ALLOWED_ZONE;

    }

    private CourierMoves saveCourierMoves(Courier courier, Store store) {
        final var entity = CourierMoves.builder()
                .lat(courier.getLat())
                .lng(courier.getLng())
                .store(store)
                .distance(calculateDistance(courier.getLat(), courier.getLng(), store.getLat(), store.getLng()))
                .courier(courier)
                .allowedTime(courier.getTime().plusMinutes(1))
                .build();
        return repository.save(entity);
    }

}
