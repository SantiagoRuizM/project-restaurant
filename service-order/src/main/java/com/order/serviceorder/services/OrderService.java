package com.order.serviceorder.services;

import com.order.serviceorder.dtos.PageGeneric;
import com.order.serviceorder.dtos.dish.DishForOrderDto;
import com.order.serviceorder.dtos.dish.DishResponseDto;
import com.order.serviceorder.dtos.order.OrderRequestDto;
import com.order.serviceorder.dtos.order.OrderResponseDto;
import com.order.serviceorder.entities.EmployeeEntity;
import com.order.serviceorder.entities.UserEntity;
import com.order.serviceorder.exceptions.*;
import com.order.serviceorder.externals.DishEntity;
import com.order.serviceorder.entities.OrderEntity;
import com.order.serviceorder.mappers.DishMapper;
import com.order.serviceorder.mappers.EmployeeMapper;
import com.order.serviceorder.mappers.OrderMapper;
import com.order.serviceorder.mappers.UserMapper;
import com.order.serviceorder.repositories.OrderRepository;
import com.order.serviceorder.repositories.UserRepository;
import com.order.serviceorder.validations.OrderValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService extends OrderValidations {

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
    private EmployeeMapper employeeMapper;
    @Autowired
    private RestTemplate restTemplate;

    public void createOrder(OrderRequestDto dto) {
        validateUserExists(!userRepository.existsById(dto.getUser()), dto.getUser());
        UserEntity user = userRepository.findById(dto.getUser()).get();
        validateUserOrderActive(user.isOrderActive(), dto.getUser());
        OrderEntity order = orderMapper.requestToOrder(dto);
        String dishes = "";
        for ( DishForOrderDto value : dto.getDish() ) {
            DishEntity entity;
            try {
                entity = restTemplate.getForObject("http://localhost:8081/serviceDishes/dishes/get/" + value.getIdDish(), DishEntity.class);
            } catch (Exception e) {
                throw new DishFailedResponseController(e.getMessage());
            }
            validateDishActive(!entity.isActive(), value.getIdDish());
            validateDishCampus(entity.getCampus().getId(), dto.getCampus(), value.getIdDish());
            dishes += value.getIdDish() + " " + value.getQuantity() + " ";
        }
        order.setDishes(dishes);
        orderRepository.save(order);
        user.setOrderActive(!user.isOrderActive());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrdersGeneric(List<OrderEntity> orderEntities) {
        try {
            List<OrderResponseDto> responsesDto = new ArrayList<>();
            for ( OrderEntity order : orderEntities ) {
                OrderResponseDto responseDto = new OrderResponseDto(order.getState(), userMapper.entityToRequest(order.getUserOrder()), employeeMapper.entityToRequest(order.getEmployeeOrder()));
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
                responsesDto.add(responseDto);
            }
            return responsesDto;
        } catch (Exception e) {
            throw new DishFailedResponseController(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        try {
            return getAllOrdersGeneric(orderRepository.findAll());
        } catch (Exception e) {
            throw new DishFailedResponseController(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public PageGeneric<List<OrderResponseDto>> getAllOrdersStateCampus(String state, Long campus, int page) {
        try {
            List<OrderResponseDto> ordersResponsesDto = getAllOrdersGeneric(orderRepository.findByStateAndCampus(state, campus));
            validatePage(page, ordersResponsesDto.size());
            List<OrderResponseDto> responseDto = ordersResponsesDto.subList(page * 10, Math.min(page * 10 + 10, ordersResponsesDto.size()));
            Page<OrderResponseDto> info = new PageImpl<>(ordersResponsesDto, PageRequest.of(page, 10), ordersResponsesDto.size());
            return new PageGeneric<>(info.getTotalPages(), page + 1, responseDto.size(), info.getContent());
        } catch (Exception e) {
            throw new DishFailedResponseController(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public PageGeneric<List<OrderResponseDto>> getAllOrdersState(String state, int page) {
        try {
            List<OrderResponseDto> ordersResponsesDto = getAllOrdersGeneric(orderRepository.findByState(state));
            validatePage(page, ordersResponsesDto.size());
            List<OrderResponseDto> responseDto = ordersResponsesDto.subList(page * 10, Math.min(page * 10 + 10, ordersResponsesDto.size()));
            Page<OrderResponseDto> info = new PageImpl<>(ordersResponsesDto, PageRequest.of(page, 10), ordersResponsesDto.size());
            return new PageGeneric<>(info.getTotalPages(), page + 1, responseDto.size(), info.getContent());
        } catch (Exception e) {
            throw new DishFailedResponseController(e.getMessage());
        }
    }

    public void updateOrderEmployeeState(Long id, Long employee) {
        Optional<OrderEntity> request = orderRepository.findById(id);
        validateOrderPresent(request, id);
        OrderEntity data = request.get();
        data.setState("En preparación");
        data.setEmployeeOrder(new EmployeeEntity(employee));
        orderRepository.save(data);
    }
}
