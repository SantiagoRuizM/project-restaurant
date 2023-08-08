package com.order.serviceorder.mappers;

import com.order.serviceorder.dtos.employee.EmployeeRequestDto;
import com.order.serviceorder.dtos.employee.EmployeeResponseDto;
import com.order.serviceorder.entities.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    EmployeeEntity requestToEntity(EmployeeRequestDto requestDto);
    List<EmployeeEntity> requestsToEntities(List<EmployeeRequestDto> requestsDto);

    EmployeeRequestDto entityToRequest(EmployeeEntity employee);
    List<EmployeeRequestDto> entitiesToRequests(List<EmployeeEntity> employeeEntities);

    EmployeeResponseDto entityToResponse(EmployeeEntity employee);
    List<EmployeeResponseDto> entitiesToResponses(List<EmployeeEntity> employeeEntities);
}
