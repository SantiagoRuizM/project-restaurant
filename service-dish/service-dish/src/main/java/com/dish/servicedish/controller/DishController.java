package com.dish.servicedish.controller;

import com.dish.servicedish.dtos.dish.DishRequestDto;
import com.dish.servicedish.dtos.dish.DishResponseDto;
import com.dish.servicedish.dtos.dish.DishUpdateRequestDto;
import com.dish.servicedish.dtos.PageGeneric;
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
    public ResponseEntity<Map<String, String>> createDish(@RequestBody DishRequestDto dish) {
        service.createDish(dish);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Created success!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DishResponseDto> getDish(@PathVariable Long id) {
        return new ResponseEntity<>(service.getDish(id), HttpStatus.OK);
    }

    @GetMapping("/getAll/categoryCampus")
    public ResponseEntity<PageGeneric<List<DishResponseDto>>> getAllDishesCategoryCampus(@RequestParam(required = false) Long category, @RequestParam(required = false) Long campus, @RequestParam(value = "1")  int page) {
        return new ResponseEntity<>(service.getAllDishesCategoryCampus(category, campus, page - 1), HttpStatus.OK);
    }

    @GetMapping("/getAll/actives")
    public ResponseEntity<List<DishResponseDto>> getAllDishesActive() {
        return new ResponseEntity<>(service.getAllDishesActive(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DishResponseDto> updateDish(@PathVariable Long id, @RequestBody DishUpdateRequestDto dish) {
        return new ResponseEntity<>(service.updateDishPriceCampusDescription(id, dish), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/state/{id}")
    public ResponseEntity<DishResponseDto> updateDish(@PathVariable Long id) {
        return new ResponseEntity<>(service.updateDishState(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteDish(@PathVariable Long id) {
        if (service.deleteDish(id)) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Deleted success!");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
