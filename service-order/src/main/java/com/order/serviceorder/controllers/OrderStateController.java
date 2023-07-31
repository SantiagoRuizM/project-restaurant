package com.order.serviceorder.controllers;

import com.order.serviceorder.dtos.orderState.OrderStateResponseDto;
import com.order.serviceorder.entities.OrderStateEntity;
import com.order.serviceorder.services.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orderStates")
public class OrderStateController {

    @Autowired
    private OrderStateService orderStateService;

    @GetMapping("/getAll/{numberOrder}")
    public ResponseEntity<List<OrderStateResponseDto>> getAllOrderStatesNumberOrder(@PathVariable Long numberOrder) {
        return new ResponseEntity<>(orderStateService.getAllOrderStatesNumberOrder(numberOrder), HttpStatus.OK);
    }
}
