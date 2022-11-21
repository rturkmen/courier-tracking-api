package com.migros.courier.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.courier.model.response.BaseResponse;
import com.migros.courier.service.CourierMovesService;
import com.migros.courier.service.CourierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.migros.courier.util.Generator.createCourierRequest;
import static com.migros.courier.util.Generator.createCourierUpdateRequest;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourierController.class)
public class CourierControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CourierMovesService courierMovesService;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CourierService courierService;

    @Test
    void createCourierTest() throws Exception {
        var url = "/courier/create";
        var request = createCourierRequest();
        when(courierService.createNewCourier(request)).thenReturn(new BaseResponse());
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
        verify(courierService).createNewCourier(request);
    }

    @Test
    void updateCourierTest() throws Exception {
        var url = "/courier/update";
        var request = createCourierUpdateRequest();
        when(courierService.updateCourier(request)).thenReturn(new BaseResponse());
        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
        verify(courierService).updateCourier(request);
    }

}
