package com.migros.courier.util;

import com.migros.courier.model.CourierModel;
import com.migros.courier.model.CourierTotalDistanceModel;
import com.migros.courier.model.StoreModel;
import com.migros.courier.model.request.CourierRequest;
import com.migros.courier.model.request.CourierUpdateRequest;
import com.migros.courier.model.request.StoreRequest;
import com.migros.courier.model.request.StoreUpdateRequest;
import com.migros.courier.persistence.entity.Courier;
import com.migros.courier.persistence.entity.CourierMoves;
import com.migros.courier.persistence.entity.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.migros.courier.constant.TestConstants.*;

public class Generator {

    public static CourierRequest createCourierRequest() {
        return CourierRequest.builder()
                .lng(LONGITUDE)
                .lat(LATITUDE)
                .identityNumber(COURIER_ID)
                .time(getLocalDateTime(LOCAL_DATE_TIME_STR))
                .build();
    }

    public static CourierUpdateRequest createCourierUpdateRequest() {
        return CourierUpdateRequest.builder()
                .lng(LONGITUDE)
                .lat(LATITUDE)
                .identityNumber(COURIER_ID)
                .time(getLocalDateTime(LOCAL_DATE_TIME_STR))
                .build();
    }

    public static Courier createCourierEntity() {
        return Courier.builder()
                .lng(LONGITUDE)
                .lat(LATITUDE)
                .identityNumber(COURIER_ID)
                .time(getLocalDateTime(LOCAL_DATE_TIME_STR))
                .build();
    }

    public static CourierModel createCourierModel() {
        return CourierModel.builder()
                .lng(LONGITUDE)
                .lat(LATITUDE)
                .courierMoves(new ArrayList<>())
                .identityNumber(COURIER_ID)
                .time(getLocalDateTime(LOCAL_DATE_TIME_STR))
                .build();
    }

    public static CourierMoves createCourierMoves() {
        return CourierMoves.builder()
                .lng(LONGITUDE)
                .lat(LATITUDE)
                .courier(createCourierEntity())
                .distance(DISTANCE)
                .allowedTime(getLocalDateTime(LOCAL_DATE_TIME_STR))
                .build();
    }

    public static CourierTotalDistanceModel createCourierTotalDistanceModel() {
        return CourierTotalDistanceModel.builder()
                .courierId(COURIER_ID)
                .totalDistance(DISTANCE)
                .courierMoves(new ArrayList<>())
                .build();
    }

    public static StoreRequest createStoreRequest() {
        return StoreRequest.builder()
                .lat(LATITUDE)
                .lng(LONGITUDE)
                .name("Ataşehir MMM Migros")
                .build();
    }

    public static Store createStore(String name) {
        return Store.builder()
                .lat(LATITUDE)
                .lng(LONGITUDE)
                .name(name)
                .build();
    }

    public static List<Store> createStoreList() {
        return List.of(createStore(ATASEHIR_MIGROS), createStore(NOVADA_MIGROS));
    }

    public static StoreModel createStoreModel(String name) {
        return StoreModel.builder()
                .lat(LATITUDE)
                .lng(LONGITUDE)
                .name(name)
                .build();
    }


    public static StoreUpdateRequest createStoreUpdateRequest() {
        return StoreUpdateRequest.builder()
                .lat(LATITUDE)
                .lng(LONGITUDE)
                .storeId(1L)
                .build();
    }

    public static Courier createCourierForMoves() {
        return Courier.builder()
                .lat(LATITUDE1)
                .lng(LONGITUDE1)
                .identityNumber(COURIER_ID)
                .time(getLocalDateTime(LOCAL_DATE_TIME_STR)).build();
    }

    private static LocalDateTime getLocalDateTime(String val) {
        return LocalDateTime.parse(val);
    }
}
