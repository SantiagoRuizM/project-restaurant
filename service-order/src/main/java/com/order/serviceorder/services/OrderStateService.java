package com.order.serviceorder.services;

import com.order.serviceorder.dtos.orderState.OrderStateResponseDto;
import com.order.serviceorder.entities.OrderStateEntity;
import com.order.serviceorder.mappers.OrderStateMapper;
import com.order.serviceorder.repositories.OrderStateRepository;
import com.order.serviceorder.validations.OrderStateValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class OrderStateService extends OrderStateValidations {

    @Autowired
    private OrderStateRepository orderStateRepository;
    @Autowired
    private OrderStateMapper orderStateMapper;

    public List<OrderStateResponseDto> getAllOrderStatesNumberOrder(Long numberOrder) {
        List<OrderStateEntity> orders = orderStateRepository.findByNumberOrderOrderById(numberOrder);
        validateOrderStatesPresent(orders, numberOrder);
        List<OrderStateResponseDto> responseDto = orderStateMapper.entitiesToResponses(orders);
        for (int i = 0; i < responseDto.size(); i++) {
            if (orders.get(i).getEndState() == null) responseDto.get(i).setTimeState("In process...");
            else responseDto.get(i).setTimeState(Duration.between(orders.get(i).getStartState(), orders.get(i).getEndState()).toMinutes() + " minutes");
        }
        return responseDto;
    }
}
