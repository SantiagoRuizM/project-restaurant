package com.order.serviceorder.services;

import com.order.serviceorder.dtos.PageGeneric;
import com.order.serviceorder.dtos.dish.DishForOrderDto;
import com.order.serviceorder.dtos.dish.DishResponseDto;
import com.order.serviceorder.dtos.employee.EmployeeResponseDto;
import com.order.serviceorder.dtos.order.*;
import com.order.serviceorder.entities.*;
import com.order.serviceorder.exceptions.*;
import com.order.serviceorder.externals.CampusEntity;
import com.order.serviceorder.externals.DishEntity;
import com.order.serviceorder.mappers.DishMapper;
import com.order.serviceorder.mappers.EmployeeMapper;
import com.order.serviceorder.mappers.OrderMapper;
import com.order.serviceorder.mappers.UserMapper;
import com.order.serviceorder.repositories.*;
import com.order.serviceorder.validations.OrderValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService extends OrderValidations {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderDishDetailsRepository orderDishDetailsRepository;
    @Autowired
    private OrderStateRepository orderStateRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
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
        for ( DishForOrderDto value : dto.getDish() ) {
            DishEntity entity;
            try {
                entity = restTemplate.getForObject("http://localhost:8081/serviceDishes/dishes/get/" + value.getIdDish(), DishEntity.class);
            } catch (Exception e) {
                throw new DishFailedResponseControllerException(e.getMessage());
            }
            validateDishActive(!entity.isActive(), value.getIdDish());
            validateDishCampus(entity.getCampus().getId(), dto.getCampus(), value.getIdDish());
        }
        OrderEntity orderEntity = orderRepository.save(order);
        for ( DishForOrderDto value : dto.getDish() ) {
            orderDishDetailsRepository.save(new OrderDishDetailsEntity(value.getQuantity(), orderEntity, value.getIdDish()));
        }
        orderStateRepository.save(new OrderStateEntity(orderEntity.getId(), "Pendiente", orderEntity.getUserOrder(), null));
        user.setOrderActive(!user.isOrderActive());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrdersGeneric(List<OrderEntity> orderEntities) {
        try {
            List<OrderResponseDto> responsesDto = new ArrayList<>();
            for ( OrderEntity order : orderEntities ) {
                OrderResponseDto responseDto = new OrderResponseDto(order.getState(), userMapper.entityToRequest(order.getUserOrder()), employeeMapper.entityToRequest(order.getEmployeeOrder()), order.getDeliveryId());
                List<OrderDishDetailsEntity> dishesEntity = orderDishDetailsRepository.findByOrderDish(order);
                List<DishResponseDto> dishEntities = new ArrayList<>();
                for ( OrderDishDetailsEntity dishDetails : dishesEntity) {
                    DishEntity entity = restTemplate.getForObject("http://localhost:8081/serviceDishes/dishes/get/" + dishDetails.getDish(), DishEntity.class);
                    DishResponseDto dish = dishMapper.entityToResponse(entity);
                    responseDto.setCampus(entity.getCampus());
                    dish.setQuantity(dishDetails.getQuantity());
                    dishEntities.add(dish);
                }
                responseDto.setDishes(dishEntities);
                responsesDto.add(responseDto);
            }
            return responsesDto;
        } catch (Exception e) {
            throw new DishFailedResponseControllerException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        try {
            return getAllOrdersGeneric(orderRepository.findAllByOrderById());
        } catch (Exception e) {
            throw new DishFailedResponseControllerException(e.getMessage());
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
            throw new DishFailedResponseControllerException(e.getMessage());
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
            throw new DishFailedResponseControllerException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<OrderTimeResponseDto> getAllOrdersTime() {
        return orderRepository.findByEndOrderIsNotNull()
                .stream().map(orderEntity -> {
                    Duration duration = Duration.between(orderEntity.getStartOrder(), orderEntity.getEndOrder());
                    return new OrderTimeResponseDto(orderEntity.getId(), duration.toMinutes() + " minutes");
                }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderTimeEmployeeResponseDto> getAllOrdersTimeUsers() {
        List<OrderTimeEmployeeResponseDto> responsesDto = new ArrayList<>();
        List<EmployeeEntity> employees = employeeRepository.findAll();
        for ( EmployeeEntity employee : employees ) {
            long quantityOrders = 0;
            OrderTimeEmployeeResponseDto responseDto = new OrderTimeEmployeeResponseDto(new EmployeeResponseDto(employee.getName()));
            Duration durationGeneral = Duration.ZERO;
            List<OrderEntity> orders = orderRepository.findByEndOrderIsNotNullAndEmployeeOrder(employee);
            for ( OrderEntity order : orders ) {
                durationGeneral = durationGeneral.plus(Duration.between(order.getStartOrder(), order.getEndOrder()));
                quantityOrders++;
            }
            if (quantityOrders == 0) responseDto.setTimeOrder("0 minutes");
            else responseDto.setTimeOrder((durationGeneral.toMinutes() / quantityOrders) + " minutes");
            responseDto.setQuantityOrders((int) quantityOrders);
            responsesDto.add(responseDto);
        }
        return responsesDto;
    }

    @Transactional(readOnly = true)
    public OrderClaimResponseDto getAllOrdersClaim(String deliveryId) {
        Optional<OrderEntity> orderEntity = orderRepository.findByDeliveryId(deliveryId);
        validateOrderPresent(orderEntity, deliveryId);
        List<OrderDishDetailsEntity> dishesEntity = orderDishDetailsRepository.findByOrderDish(orderEntity.get());
        CampusEntity campus = new CampusEntity();
        for ( OrderDishDetailsEntity dishDetails : dishesEntity) {
            try {
                campus = restTemplate.getForObject("http://localhost:8081/serviceDishes/dishes/get/" + dishDetails.getDish(), DishEntity.class).getCampus();
            } catch (Exception e) {
                throw new DishFailedResponseControllerException(e.getMessage());
            }
            break;
        }
        return orderMapper.orderToResponse(new OrderResponseDto(campus, deliveryId));
    }

    public void updateOrderEmployee(Long id, Long employee) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        validateOrderPresent(order, id);
        OrderEntity data = order.get();
        validateStateNotEarringForAssignEmployee(data.getState(), id);
        validateEmployeeExists(!employeeRepository.existsById(employee), employee);
        data.setEmployeeOrder(new EmployeeEntity(employee));
        data.setState("En preparación");
        orderRepository.save(data);
        OrderStateEntity orderStateEntity = orderStateRepository.findByNumberOrderAndState(id, "Pendiente");
        orderStateEntity.setEndState(LocalDateTime.now());
        orderStateRepository.saveAll(List.of(orderStateEntity, new OrderStateEntity(data.getId(), data.getState(), data.getUserOrder(), null)));
    }

    public String[] updateOrderState(Long id) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        validateOrderPresent(order, id);
        OrderEntity data = order.get();
        validateStateEarring(data.getState(), id);
        validateStateFinish(data.getState(), id);
        validateStateCancelled(data.getState(), id);
        String[] state = new String[2];
        LocalDateTime localDateTime = null;
        String stateBefore = "";
        switch (data.getState()) {
            case "En preparación":
                data.setState("Listo");
                stateBefore = "En preparación";
                data.setEndOrder(LocalDateTime.now());
                data.setDeliveryId(data.getCampus() + data.getUserOrder().getName() + id + LocalDateTime.now());
                state[1] = data.getDeliveryId();
                break;
            case "Listo":
                data.setState("Entregado");
                stateBefore = "Listo";
                UserEntity user = userRepository.findById(data.getUserOrder().getId()).get();
                user.setOrderActive(!user.isOrderActive());
                userRepository.save(user);
                localDateTime = LocalDateTime.now();
                break;
        }
        orderRepository.save(data);
        OrderStateEntity orderStateEntity = orderStateRepository.findByNumberOrderAndState(id, stateBefore);
        orderStateEntity.setEndState(LocalDateTime.now());
        orderStateRepository.saveAll(List.of(orderStateEntity, new OrderStateEntity(data.getId(), data.getState(), data.getUserOrder(), localDateTime)));
        state[0] = data.getState();
        return state;
    }

    public void updateOrderCancelled(OrderCanceledRequestDto requestDto) {
        Optional<OrderEntity> order = orderRepository.findById(requestDto.getNumberOrder());
        validateOrderPresent(order, requestDto.getNumberOrder());
        OrderEntity data = order.get();
        validateStateCancelled(data.getState(), data.getId());
        validateStateNotEarringForCancelled(data.getState(), data.getId());
        data.setState("Cancelado");
        orderRepository.save(data);
        orderStateRepository.save(new OrderStateEntity(data.getId(), data.getState(), data.getUserOrder(), LocalDateTime.now()));
        UserEntity user = userRepository.findById(data.getUserOrder().getId()).get();
        user.setOrderActive(!user.isOrderActive());
        userRepository.save(user);
    }
}
