package com.order_management_system.controller;

import com.order_management_system.dto.erro.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponseDto dto = new ErrorResponseDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

}
