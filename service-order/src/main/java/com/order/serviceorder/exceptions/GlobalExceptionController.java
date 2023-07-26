package com.order.serviceorder.exceptions;

import com.order.serviceorder.dtos.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(DishFailedResponseController.class)
    public ResponseEntity<ResponseErrorDto> handlerDishFailedResponseController(DishFailedResponseController dishFailedResponseController) {
        return new ResponseEntity<>(new ResponseErrorDto(dishFailedResponseController.getMessage(), HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
