package com.order.serviceorder.services;

import com.order.serviceorder.dtos.PageGeneric;
import com.order.serviceorder.dtos.dish.DishForOrderDto;
import com.order.serviceorder.dtos.dish.DishResponseDto;
import com.order.serviceorder.dtos.order.OrderRequestDto;
import com.order.serviceorder.dtos.order.OrderResponseDto;
import com.order.serviceorder.entities.UserEntity;
import com.order.serviceorder.exceptions.*;
import com.order.serviceorder.externals.DishEntity;
import com.order.serviceorder.entities.OrderEntity;
import com.order.serviceorder.mappers.DishMapper;
import com.order.serviceorder.mappers.OrderMapper;
import com.order.serviceorder.mappers.UserMapper;
import com.order.serviceorder.repositories.OrderRepository;
import com.order.serviceorder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;

    public void createOrder(OrderRequestDto dto) {
        if (userRepository.existsById(dto.getUser())) {
            UserEntity user = userRepository.findById(dto.getUser()).get();
            if (user.isOrderActive()) throw new OrderInProcessException("The user with id " + dto.getUser() + ": already have an order in process");
        } else throw new RecordNotFoundException("The user with id " + dto.getUser() + ": was not found");
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
        orderRepository.save(order);
        UserEntity user = userRepository.findById(dto.getUser()).get();
        user.setOrderActive(!user.isOrderActive());
        userRepository.save(user);
    }

    public List<OrderResponseDto> getAllOrders() {
        try {
            List<OrderEntity> orderEntities = orderRepository.findAll();
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

    public PageGeneric<List<OrderResponseDto>> getAllOrdersStateCampus(String state, Long campus, int page) {
        try {
            List<OrderEntity> orderEntities = orderRepository.findByStateAndCampus(state, campus);
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
            List<OrderResponseDto> responseDto = responseDtos.subList(page * 10, Math.min(page * 10 + 10, responseDtos.size()));
            Page<OrderResponseDto> info = new PageImpl<>(responseDtos, PageRequest.of(page, 10), responseDtos.size());
            return new PageGeneric<>(info.getTotalPages(), page + 1, responseDto.size(), info.getContent());
        } catch (Exception e) {
            throw new DishFailedResponseController(e.getMessage());
        }
    }
}
