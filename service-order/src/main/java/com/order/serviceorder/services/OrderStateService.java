package com.order.serviceorder.services;

import com.order.serviceorder.dtos.orderState.OrderStateResponseDto;
import com.order.serviceorder.entities.OrderStateEntity;
import com.order.serviceorder.mappers.OrderStateMapper;
import com.order.serviceorder.repositories.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStateService {

    @Autowired
    private OrderStateRepository orderStateRepository;
    @Autowired
    private OrderStateMapper orderStateMapper;

    public List<OrderStateResponseDto> getAllOrderStatesNumberOrder(Long numberOrder) {
        return orderStateMapper.entitiesToResponses(orderStateRepository.findByNumberOrderOrderByNumberOrderAsc(numberOrder));
    }
}
