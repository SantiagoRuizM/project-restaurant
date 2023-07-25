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

    @GetMapping("/getAll")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return new ResponseEntity<>(service.getAllDishes(), HttpStatus.OK);
    }

    @GetMapping("/getAll/{category}/{campus}")
    public ResponseEntity<List<DishResponseDto>> getAllDishesCategoryCampus(@PathVariable Long category, @PathVariable Long campus) {
        return new ResponseEntity<>(service.getAllDishesCategoryCampus(category, campus), HttpStatus.OK);
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
