package com.dish.servicedish.exceptions;

import com.dish.servicedish.dtos.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(WrongRoleException.class)
    public ResponseEntity<ResponseErrorDto> handlerWrongRoleException(WrongRoleException wrongRoleException) {
        return new ResponseEntity<>(new ResponseErrorDto(wrongRoleException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handlerRecordNotFoundException(RecordNotFoundException recordNotFoundException) {
        return new ResponseEntity<>(new ResponseErrorDto(recordNotFoundException.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PriceNegativeException.class)
    public ResponseEntity<ResponseErrorDto> handlerPriceNegativeException(PriceNegativeException priceNegativeException) {
        return new ResponseEntity<>(new ResponseErrorDto(priceNegativeException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPageException.class)
    public ResponseEntity<ResponseErrorDto> handlerInvalidPageException(InvalidPageException invalidPageException) {
        return new ResponseEntity<>(new ResponseErrorDto(invalidPageException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
