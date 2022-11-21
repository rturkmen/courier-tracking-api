package com.migros.courier.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.courier.model.response.BaseResponse;
import com.migros.courier.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.migros.courier.util.Generator.createStoreRequest;
import static com.migros.courier.util.Generator.createStoreUpdateRequest;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreController.class)
public class StoreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StoreService storeService;

    @Test
    void createStoreTest() throws Exception {
        var url = "/store/create";
        var request = createStoreRequest();
        when(storeService.createNewStore(request)).thenReturn(new BaseResponse());
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
        verify(storeService).createNewStore(request);
    }

    @Test
    void updateStoreTest() throws Exception {
        var url = "/store/update";
        var request = createStoreUpdateRequest();
        when(storeService.updateStore(request)).thenReturn(new BaseResponse());
        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
        verify(storeService).updateStore(request);
    }


}
