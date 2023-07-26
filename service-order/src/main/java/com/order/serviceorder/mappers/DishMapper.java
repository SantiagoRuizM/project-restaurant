package com.order.serviceorder.mappers;

import com.order.serviceorder.dtos.DishResponseDto;
import com.order.serviceorder.entities.DishEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(target = "quantity", ignore = true)
    DishResponseDto entityToResponse(DishEntity entity);
    List<DishResponseDto> entitiesToResponses(List<DishEntity> entities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "urlImage", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "campus", ignore = true)
    })
    DishEntity responseToEntity(DishResponseDto responseDto);
    List<DishEntity> responsesToEntities(List<DishResponseDto> responsesDto);
}
