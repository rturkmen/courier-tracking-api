package com.migros.courier.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.migros.courier.model.constants.ApiConstants.SUCCESS;
import static com.migros.courier.model.constants.ApiConstants.SUCCESS_CODE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private Object data;
    private String message;
    private int code;

    public BaseResponse(Object data) {
        this.data = data;
        this.message = SUCCESS;
        this.code = SUCCESS_CODE;
    }
}
