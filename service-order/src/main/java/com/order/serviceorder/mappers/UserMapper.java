package com.order.serviceorder.mappers;

import com.order.serviceorder.dtos.user.UserRequestDto;
import com.order.serviceorder.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "orderActive", ignore = true)
    })
    UserEntity requestToEntity(UserRequestDto requestDto);
    List<UserEntity> requestsToEntities(List<UserRequestDto> requestsDto);

    UserRequestDto entityToRequest(UserEntity user);
    List<UserRequestDto> entitiesToRequests(List<UserEntity> userEntities);
}
