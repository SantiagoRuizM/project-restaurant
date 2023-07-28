package com.order.serviceorder.controllers;

import com.order.serviceorder.dtos.employee.EmployeeRequestDto;
import com.order.serviceorder.mappers.EmployeeMapper;
import com.order.serviceorder.services.EmployeeService;
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
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    @Autowired
    private EmployeeMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody EmployeeRequestDto dto) {
        service.createEmployee(mapper.requestToEntity(dto));
        Map<String, String> map = new HashMap<>();
        map.put("message", "Created success!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
