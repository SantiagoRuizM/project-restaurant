package com.order.serviceorder.controllers;

import com.order.serviceorder.dtos.order.OrderRequestDto;
import com.order.serviceorder.dtos.user.UserRequestDto;
import com.order.serviceorder.mappers.UserMapper;
import com.order.serviceorder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody UserRequestDto dto) {
        service.createUser(mapper.requestToEntity(dto));
        Map<String, String> map = new HashMap<>();
        map.put("message", "Created success!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
