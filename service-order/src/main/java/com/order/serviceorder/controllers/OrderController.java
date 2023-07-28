package com.order.serviceorder.controllers;

import com.order.serviceorder.dtos.PageGeneric;
import com.order.serviceorder.dtos.order.OrderRequestDto;
import com.order.serviceorder.dtos.order.OrderResponseDto;
import com.order.serviceorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody OrderRequestDto dto) {
        service.createOrder(dto);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Created success!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/getAll/{state}/{campus}")
    public ResponseEntity<PageGeneric<List<OrderResponseDto>>> getAllOrdersStateCampus(@PathVariable String state, @PathVariable Long campus, @RequestParam(value = "1")  int page) {
        return new ResponseEntity<>(service.getAllOrdersStateCampus(state, campus, page - 1), HttpStatus.OK);
    }

    @GetMapping("/getAll/{state}")
    public ResponseEntity<PageGeneric<List<OrderResponseDto>>> getAllOrdersState(@PathVariable String state, @RequestParam(value = "1") int page) {
        return new ResponseEntity<>(service.getAllOrdersState(state, page - 1), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }
}
