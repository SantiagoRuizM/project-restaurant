package com.order.serviceorder.services;

import com.order.serviceorder.dtos.dish.DishForOrderDto;
import com.order.serviceorder.dtos.dish.DishResponseDto;
import com.order.serviceorder.dtos.order.OrderRequestDto;
import com.order.serviceorder.dtos.order.OrderResponseDto;
import com.order.serviceorder.externals.DishEntity;
import com.order.serviceorder.entities.OrderEntity;
import com.order.serviceorder.exceptions.DishFailedResponseController;
import com.order.serviceorder.exceptions.InactiveDishException;
import com.order.serviceorder.exceptions.IncorrectDishCampusException;
import com.order.serviceorder.mappers.DishMapper;
import com.order.serviceorder.mappers.OrderMapper;
import com.order.serviceorder.mappers.UserMapper;
import com.order.serviceorder.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;

    public void createOrder(OrderRequestDto dto) {
        OrderEntity order = orderMapper.requestToOrder(dto);
        String dishes = "";
        for ( DishForOrderDto value : dto.getDish() ) {
            DishEntity entity;
            try {
                entity = restTemplate.getForObject("http://localhost:8081/serviceDishes/dishes/get/" + value.getIdDish(), DishEntity.class);
            } catch (Exception e) {
                throw new DishFailedResponseController(e.getMessage());
            }
            if (entity.isActive()) dishes += value.getIdDish() + " " + value.getQuantity() + " ";
            else throw new InactiveDishException("The dish with id " + value.getIdDish() + ": is not active");
            if (entity.getCampus().getId() != dto.getCampus()) throw new IncorrectDishCampusException("The dish with id " + value.getIdDish() + ": is not valid in your campus");
        }
        order.setDishes(dishes);
        repository.save(order);
    }

    public List<OrderResponseDto> getAllOrders() {
        try {
            List<OrderEntity> orderEntities = repository.findAll();
            List<OrderResponseDto> responseDtos = new ArrayList<>();
            for ( OrderEntity order : orderEntities ) {
                OrderResponseDto responseDto = new OrderResponseDto();
                responseDto.setState(order.getState());
                responseDto.setUser(userMapper.entityToRequest(order.getUserOrder()));
                String[] dishes = order.getDishes().split(" ");
                List<DishResponseDto> dishEntities = new ArrayList<>();
                for (int i = 0; i < dishes.length; i += 2) {
                    DishEntity entity = restTemplate.getForObject("http://localhost:8081/serviceDishes/dishes/get/" + dishes[i], DishEntity.class);
                    DishResponseDto dish = dishMapper.entityToResponse(entity);
                    if (i == 0) responseDto.setCampus(entity.getCampus());
                    dish.setQuantity(Integer.parseInt(dishes[i + 1]));
                    dishEntities.add(dish);
                }
                responseDto.setDishes(dishEntities);
                responseDtos.add(responseDto);
            }
            return responseDtos;
        } catch (Exception e) {
            throw new DishFailedResponseController(e.getMessage());
        }
    }
}
