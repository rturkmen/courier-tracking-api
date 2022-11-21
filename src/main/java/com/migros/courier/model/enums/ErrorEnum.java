package com.migros.courier.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    COURIER_IS_EXIST("Courier is exists in Migros company", 1001),
    COURIER_NOT_FOUND("Courier couldn't found in Migros company", 1001),
    STORE_IS_EXIST("Store is exists in Migros company", 1020),
    STORE_NOT_FOUND("Store couldn't found in Migros company", 1021)
    ;

    private String message;
    private int code;
}
