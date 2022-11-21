package com.migros.courier.exception;

import com.migros.courier.model.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private String message;
    private int code;

    public BusinessException(ErrorEnum error) {
        this.message = error.getMessage();
        this.code = error.getCode();
    }
}
