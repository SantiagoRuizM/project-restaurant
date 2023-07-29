package com.order.serviceorder.exceptions;

import com.order.serviceorder.dtos.ResponseErrorDto;
import com.order.serviceorder.dtos.ResponseErrorServerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(DishFailedResponseController.class)
    public ResponseEntity<ResponseErrorServerDto> handlerDishFailedResponseController(DishFailedResponseController dishFailedResponseController) {
        return new ResponseEntity<>(new ResponseErrorServerDto("The request has failed", dishFailedResponseController.getMessage(), HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(InactiveDishException.class)
    public ResponseEntity<ResponseErrorDto> handlerInactiveDishException(InactiveDishException inactiveDishException) {
        return new ResponseEntity<>(new ResponseErrorDto(inactiveDishException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectDishCampusException.class)
    public ResponseEntity<ResponseErrorDto> handlerIncorrectDishCampusException(IncorrectDishCampusException incorrectDishCampusException) {
        return new ResponseEntity<>(new ResponseErrorDto(incorrectDishCampusException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderInProcessException.class)
    public ResponseEntity<ResponseErrorDto> handlerOrderInProcessException(OrderInProcessException orderInProcessException) {
        return new ResponseEntity<>(new ResponseErrorDto(orderInProcessException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handlerRecordNotFoundException(RecordNotFoundException recordNotFoundException) {
        return new ResponseEntity<>(new ResponseErrorDto(recordNotFoundException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StateDeliveryException.class)
    public ResponseEntity<ResponseErrorDto> handlerStateDeliveryException(StateDeliveryException stateDeliveryException) {
        return new ResponseEntity<>(new ResponseErrorDto(stateDeliveryException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
