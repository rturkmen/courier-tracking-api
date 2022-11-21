package com.migros.courier.exception.advice;

import com.migros.courier.exception.BusinessException;
import com.migros.courier.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected BaseResponse handleBusinessException(BusinessException ex) {
        return toBaseResponse(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        var badRequest = HttpStatus.BAD_REQUEST;
        AtomicReference<String> errorMessage = new AtomicReference<>("");
        ex.getBindingResult().getAllErrors().forEach(error -> {
            final var fieldName = ((FieldError) error).getField();
            final var message = error.getDefaultMessage();
            errorMessage.set(fieldName + " " + message);
            log.error("Error Code : {} , Message : {}", badRequest.value(), errorMessage);
        });
        return toBaseResponse(errorMessage.toString(), badRequest.value());
    }

    private BaseResponse toBaseResponse(String message, int code) {
        return new BaseResponse(null, message, code);
    }
}
