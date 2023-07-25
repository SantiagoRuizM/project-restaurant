package com.dish.servicedish.service;

import com.dish.servicedish.dtos.DishRequestDto;
import com.dish.servicedish.dtos.DishResponseDto;
import com.dish.servicedish.dtos.DishUpdateRequestDto;
import com.dish.servicedish.entity.DishEntity;
import com.dish.servicedish.exceptions.PriceNegativeException;
import com.dish.servicedish.exceptions.RecordNotFoundException;
import com.dish.servicedish.exceptions.WrongRoleException;
import com.dish.servicedish.mapper.DishMapper;
import com.dish.servicedish.repository.DishRepository;
import com.dish.servicedish.validations.DishValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DishService extends DishValidations {

    @Autowired
    private DishRepository repository;
    @Autowired
    private DishMapper mapper;

    public DishResponseDto createDishe(DishRequestDto dish) {
        if (validateRole(dish.getRole())) {
            throw new WrongRoleException("The role to create dish is not allowed");
        } else if (validatePriceNegative(dish.getPrice())) {
            throw new PriceNegativeException("The price must be positive");
        } else return mapper.entityToResponse(repository.save(mapper.requestToEntity(dish)));
    }

    public DishResponseDto getDishe(Long id) {
        DishEntity dishe = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The dish was not found"));
        return mapper.entityToResponse(dishe);
    }

    public List<DishResponseDto> getAllDishes() {
        return mapper.entitiesToResponses(repository.findAll());
    }

    public DishResponseDto updateDishe(Long id, DishUpdateRequestDto dish) {
        Optional<DishEntity> request = repository.findById(id);
        if (request.isPresent()) {
            DishEntity data = request.get();
            data.setPrice(dish.getPrice());
            data.setCampus(dish.getCampus());
            data.setDescription(dish.getDescription());
            return mapper.entityToResponse(repository.save(data));
        } else throw new RecordNotFoundException("The dish was not found");
    }

    public boolean deleteDishe(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        } else throw new RecordNotFoundException("The plate was not found");
    }
}
