package com.migros.courier.pojo;

import com.migros.courier.model.CourierModel;
import com.migros.courier.model.CourierMovesModel;
import com.migros.courier.model.CourierTotalDistanceModel;
import com.migros.courier.model.StoreModel;
import com.migros.courier.model.request.CourierRequest;
import com.migros.courier.model.request.CourierUpdateRequest;
import com.migros.courier.model.request.StoreRequest;
import com.migros.courier.model.request.StoreUpdateRequest;
import com.migros.courier.model.response.BaseResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.meanbean.test.BeanTester;
import org.meanbean.test.ConfigurationBuilder;

public class TestPojos {

    @ParameterizedTest
    @ValueSource(classes = {CourierRequest.class, CourierUpdateRequest.class, StoreRequest.class, StoreUpdateRequest.class,
            BaseResponse.class, CourierModel.class, CourierMovesModel.class, CourierTotalDistanceModel.class, StoreModel.class})
    void testClazz(Class<?> clazz) {
        var configuration = new ConfigurationBuilder()
                .ignoreProperty("time")
                .ignoreProperty("allowedTime")
                .ignoreProperty("courierMoves")
                .build();
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(clazz, configuration);
    }
}
