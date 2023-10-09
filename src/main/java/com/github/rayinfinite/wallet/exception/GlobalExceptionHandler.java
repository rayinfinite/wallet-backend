package com.github.rayinfinite.wallet.exception;

import com.github.rayinfinite.wallet.model.BaseResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public BaseResponse<String> handleBindException(BindException e) {
        return BaseResponse.error(e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(DefaultException.class)
    public BaseResponse<String> handleDefaultException(DefaultException e) {
        return BaseResponse.error(e.getMessage());
    }
}
