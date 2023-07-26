package com.order.serviceorder.services;

import com.order.serviceorder.dtos.DishForOrderDto;
import com.order.serviceorder.dtos.DishResponseDto;
import com.order.serviceorder.dtos.OrderRequestDto;
import com.order.serviceorder.dtos.OrderResponseDto;
import com.order.serviceorder.entities.DishEntity;
import com.order.serviceorder.entities.OrderEntity;
import com.order.serviceorder.exceptions.DishFailedResponseController;
import com.order.serviceorder.exceptions.InactiveDishException;
import com.order.serviceorder.mappers.DishMapper;
import com.order.serviceorder.mappers.OrderMapper;
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
    private RestTemplate restTemplate;

    public void createOrder(OrderRequestDto dto) {
        try {
            OrderEntity order = orderMapper.requestToOrder(dto);
            String dishes = "";
            for ( DishForOrderDto value : dto.getDish() ) {
                DishEntity entity = restTemplate.getForObject("http://localhost:8081/serviceDishes/dishes/get/" + value.getIdDish(), DishEntity.class);
                if (entity.isActive()) dishes += value.getIdDish() + " " + value.getQuantity() + " ";
                else throw new InactiveDishException("The dish with id " + value.getIdDish() + ": is not active");
            }
            order.setDishes(dishes);
            repository.save(order);
        } catch (Exception e) {
            throw new DishFailedResponseController(e.getMessage());
        }
    }

    public List<OrderResponseDto> getAllOrders() {
        try {
            List<OrderEntity> orderEntities = repository.findAll();
            List<OrderResponseDto> responseDtos = new ArrayList<>();
            for ( OrderEntity order : orderEntities ) {
                OrderResponseDto responseDto = new OrderResponseDto();
                responseDto.setState(order.getState());
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
