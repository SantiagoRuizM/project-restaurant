package com.claim.serviceclaim.exceptions;

import com.claim.serviceclaim.dtos.ResponseErrorDto;
import com.claim.serviceclaim.dtos.ResponseErrorServerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(DishFailedResponseControllerException.class)
    public ResponseEntity<ResponseErrorServerDto> handlerDishFailedResponseControllerException(DishFailedResponseControllerException dishFailedResponseControllerException) {
        return new ResponseEntity<>(new ResponseErrorServerDto("The request has failed", dishFailedResponseControllerException.getMessage(), HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handlerRecordNotFoundException(RecordNotFoundException recordNotFoundException) {
        return new ResponseEntity<>(new ResponseErrorDto(recordNotFoundException.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
