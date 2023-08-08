package com.order.serviceorder.controllers;

import com.order.serviceorder.dtos.PageGeneric;
import com.order.serviceorder.dtos.order.*;
import com.order.serviceorder.enums.StateEnum;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/getAll/{state}/{campus}")
    public ResponseEntity<PageGeneric<List<OrderResponseDto>>> getAllOrdersStateCampus(@PathVariable StateEnum state, @PathVariable Long campus, @RequestParam(value = "1")  int page) {
        return new ResponseEntity<>(service.getAllOrdersStateCampus(state, campus, page - 1), HttpStatus.OK);
    }

    @GetMapping("/getAll/{state}")
    public ResponseEntity<PageGeneric<List<OrderResponseDto>>> getAllOrdersState(@PathVariable StateEnum state, @RequestParam(value = "1") int page) {
        return new ResponseEntity<>(service.getAllOrdersState(state, page - 1), HttpStatus.OK);
    }

    @GetMapping("/getAll/time")
    public ResponseEntity<List<OrderTimeResponseDto>> getAllOrdersTime() {
        return new ResponseEntity<>(service.getAllOrdersTime(), HttpStatus.OK);
    }

    @GetMapping("/getAll/rankingTime")
    public ResponseEntity<List<OrderTimeEmployeeResponseDto>> getAllOrdersTimeUsers() {
        return new ResponseEntity<>(service.getAllOrdersTimeUsers(), HttpStatus.OK);
    }

    @GetMapping("/get/delivery")
    public ResponseEntity<OrderClaimResponseDto> getAllOrdersClaim(@RequestParam String deliveryId) {
        return new ResponseEntity<>(service.getAllOrdersClaim(deliveryId), HttpStatus.OK);
    }

    @PutMapping("/update/stateOrder/{id}/{employee}")
    public ResponseEntity<Map<String, String>> updateOrderEmployee(@PathVariable Long id, @PathVariable Long employee) {
        service.updateOrderEmployee(id, employee);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Updated success!");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/state/{id}")
    public ResponseEntity<Map<String, String>> updateOrderState(@PathVariable Long id) {
        String[] state = service.updateOrderState(id);
        Map<String, String> map = new HashMap<>();
        if (state[0].equals(StateEnum.READY + "")) {
            map.put("message", "Updated success!");
            map.put("message to user: ", "Dear user, we inform you that your order is ready and can be claimed with the code: " + state[1]);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        map.put("message", "Updated success!");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/cancelOrder")
    public ResponseEntity<Map<String, String>> updateOrderCancelled(@RequestBody OrderCanceledRequestDto dto) {
        service.updateOrderCancelled(dto);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Updated success!");
        map.put("reasonCancellation", dto.getReasonCancellation());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
