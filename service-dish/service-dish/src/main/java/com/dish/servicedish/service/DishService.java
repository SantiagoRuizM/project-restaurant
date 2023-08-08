package com.dish.servicedish.service;

import com.dish.servicedish.dtos.dish.DishRequestDto;
import com.dish.servicedish.dtos.dish.DishResponseDto;
import com.dish.servicedish.dtos.dish.DishUpdateRequestDto;
import com.dish.servicedish.dtos.PageGeneric;
import com.dish.servicedish.entity.CampusEntity;
import com.dish.servicedish.entity.DishEntity;
import com.dish.servicedish.exceptions.RecordNotFoundException;
import com.dish.servicedish.mapper.DishMapper;
import com.dish.servicedish.repository.DishRepository;
import com.dish.servicedish.validations.DishValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DishService extends DishValidations {

    @Autowired
    private DishRepository repository;
    @Autowired
    private DishMapper mapper;

    public void createDish(DishRequestDto dish) {
        validateFactRequired(dish.getName(), "name");
        validateFactRequired(dish.getPrice(), "price");
        validateFactRequired(dish.getDescription(), "description");
        validateFactRequired(dish.getUrlImage(), "image url");
        validateFactRequired(dish.getCategory(), "category");
        validateFactRequired(dish.getCampus(), "campus");
        validatePriceNegative(dish.getPrice());
        repository.save(mapper.requestToEntity(dish));
    }

    @Transactional(readOnly = true)
    public DishResponseDto getDish(Long id) {
        Optional<DishEntity> dish = repository.findById(id);
        validateDishPresent(dish, id);
        return mapper.entityToResponse(dish.get());
    }

    @Transactional(readOnly = true)
    public PageGeneric<List<DishResponseDto>> getAllDishesCategoryCampus(Long category, Long campus, int page) {
        List<DishResponseDto> dishResponseDtoList = switch (validateFilterByCampusCategory(category, campus)) {
            case 1 -> mapper.entitiesToResponses(repository.findAll());
            case 2 -> mapper.entitiesToResponses(repository.findByCategoryIdOrCampusId(category, campus));
            default -> mapper.entitiesToResponses(repository.findByCategoryIdAndCampusId(category, campus));
        };
        validatePage(page, dishResponseDtoList.size());
        List<DishResponseDto> responseDto = dishResponseDtoList.subList(page * 10, Math.min(page * 10 + 10, dishResponseDtoList.size()));
        Page<DishResponseDto> info = new PageImpl<>(responseDto, PageRequest.of(page, 10), dishResponseDtoList.size());
        return new PageGeneric<>(info.getTotalPages(), page + 1, responseDto.size(), info.getContent());
    }

    @Transactional(readOnly = true)
    public List<DishResponseDto> getAllDishesActive() {
        return mapper.entitiesToResponses(repository.findByActive(true));
    }

    public DishResponseDto updateDishPriceCampusDescription(Long id, DishUpdateRequestDto dish) {
        validateFactRequired(dish.getPrice(), "price");
        validateFactRequired(dish.getDescription(), "description");
        validateFactRequired(dish.getCampus(), "campus");
        Optional<DishEntity> request = repository.findById(id);
        validateDishPresent(request, id);
        DishEntity data = request.get();
        data.setPrice(dish.getPrice());
        data.setCampus(new CampusEntity(dish.getCampus()));
        data.setDescription(dish.getDescription());
        return mapper.entityToResponse(repository.save(data));
    }

    public DishResponseDto updateDishState(Long id) {
        Optional<DishEntity> request = repository.findById(id);
        validateDishPresent(request, id);
        DishEntity data = request.get();
        data.setActive(!data.isActive());
        return mapper.entityToResponse(repository.save(data));
    }

    public void deleteDish(Long id) {
        validateDishPresent(repository.findById(id), id);
        repository.deleteById(id);
    }
}
