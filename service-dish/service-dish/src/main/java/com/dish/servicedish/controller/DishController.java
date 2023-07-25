package com.dish.servicedish.controller;

import com.dish.servicedish.dtos.DishRequestDto;
import com.dish.servicedish.dtos.DishResponseDto;
import com.dish.servicedish.dtos.DishUpdateRequestDto;
import com.dish.servicedish.mapper.DishMapper;
import com.dish.servicedish.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService service;
    @Autowired
    private DishMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<DishResponseDto> createDishe(@RequestBody DishRequestDto dish) {
        return new ResponseEntity<>(service.createDishe(dish), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DishResponseDto> getDishe(@PathVariable Long id) {
        return new ResponseEntity<>(service.getDishe(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return new ResponseEntity<>(service.getAllDishes(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DishResponseDto> updateDishe(@PathVariable Long id, @RequestBody DishUpdateRequestDto dish) {
        return new ResponseEntity<>(service.updateDishe(id, dish), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteDishe(@PathVariable Long id) {
        if (service.deleteDishe(id)) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Deleted success!");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
