package com.order.serviceorder.mappers;

import com.order.serviceorder.dtos.order.OrderRequestDto;
import com.order.serviceorder.dtos.order.OrderResponseDto;
import com.order.serviceorder.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, EmployeeMapper.class})
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "dishes", ignore = true),
            @Mapping(target = "state", ignore = true),
            @Mapping(target = "employeeOrder", ignore = true),
            @Mapping(source = "user", target = "userOrder.id")
    })
    OrderEntity requestToOrder(OrderRequestDto orderRequestDto);
    List<OrderEntity> requestsToOrders(List<OrderRequestDto> ordersRequestDto);

    @Mappings({
        @Mapping(target = "dish", ignore = true),
        @Mapping(source = "userOrder.id", target = "user")
    })
    OrderRequestDto orderToRequest(OrderEntity orderEntity);
    List<OrderRequestDto> ordersToRequests(List<OrderEntity> orderEntities);
}
