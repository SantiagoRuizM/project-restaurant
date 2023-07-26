package com.order.serviceorder.mappers;

import com.order.serviceorder.dtos.OrderRequestDto;
import com.order.serviceorder.entities.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "dishes", ignore = true),
            @Mapping(target = "state", ignore = true)
    })
    OrderEntity requestToOrder(OrderRequestDto orderRequestDto);
    List<OrderEntity> requestsToOrders(List<OrderRequestDto> ordersRequestDto);

    @InheritInverseConfiguration
    OrderRequestDto orderToRequest(OrderEntity orderEntity);
    List<OrderRequestDto> ordersToRequests(List<OrderEntity> orderEntities);
}
