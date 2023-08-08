package com.order.serviceorder.mappers;

import com.order.serviceorder.dtos.orderState.OrderStateResponseDto;
import com.order.serviceorder.entities.OrderStateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OrderStateMapper {

    @Mapping(target = "timeState", ignore = true)
    OrderStateResponseDto entityToResponse(OrderStateEntity entity);
    List<OrderStateResponseDto> entitiesToResponses(List<OrderStateEntity> entities);
}
