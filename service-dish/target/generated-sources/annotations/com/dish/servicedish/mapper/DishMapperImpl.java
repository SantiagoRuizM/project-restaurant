package com.dish.servicedish.mapper;

import com.dish.servicedish.dtos.dish.DishRequestDto;
import com.dish.servicedish.dtos.dish.DishResponseDto;
import com.dish.servicedish.entity.CampusEntity;
import com.dish.servicedish.entity.CategoryEntity;
import com.dish.servicedish.entity.DishEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-08-01T18:35:17-0500",
=======
    date = "2023-08-02T08:10:12-0500",
>>>>>>> d0829f25b2d025243783a5669cfd652160bd904f
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class DishMapperImpl implements DishMapper {

    @Override
    public DishRequestDto entityToRequest(DishEntity dishEntity) {
        if ( dishEntity == null ) {
            return null;
        }

        DishRequestDto dishRequestDto = new DishRequestDto();

        dishRequestDto.setCategory( dishEntityCategoryId( dishEntity ) );
        dishRequestDto.setCampus( dishEntityCampusId( dishEntity ) );
        dishRequestDto.setName( dishEntity.getName() );
        dishRequestDto.setPrice( dishEntity.getPrice() );
        dishRequestDto.setDescription( dishEntity.getDescription() );
        dishRequestDto.setUrlImage( dishEntity.getUrlImage() );

        return dishRequestDto;
    }

    @Override
    public List<DishRequestDto> entitiesToRequests(List<DishEntity> dishesEntities) {
        if ( dishesEntities == null ) {
            return null;
        }

        List<DishRequestDto> list = new ArrayList<DishRequestDto>( dishesEntities.size() );
        for ( DishEntity dishEntity : dishesEntities ) {
            list.add( entityToRequest( dishEntity ) );
        }

        return list;
    }

    @Override
    public DishEntity requestToEntity(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        DishEntity dishEntity = new DishEntity();

        dishEntity.setCategory( dishRequestDtoToCategoryEntity( dishRequestDto ) );
        dishEntity.setCampus( dishRequestDtoToCampusEntity( dishRequestDto ) );
        dishEntity.setName( dishRequestDto.getName() );
        dishEntity.setPrice( dishRequestDto.getPrice() );
        dishEntity.setDescription( dishRequestDto.getDescription() );
        dishEntity.setUrlImage( dishRequestDto.getUrlImage() );

        return dishEntity;
    }

    @Override
    public List<DishEntity> requestsToEntities(List<DishRequestDto> dishesRequestDto) {
        if ( dishesRequestDto == null ) {
            return null;
        }

        List<DishEntity> list = new ArrayList<DishEntity>( dishesRequestDto.size() );
        for ( DishRequestDto dishRequestDto : dishesRequestDto ) {
            list.add( requestToEntity( dishRequestDto ) );
        }

        return list;
    }

    @Override
    public DishResponseDto entityToResponse(DishEntity dishEntity) {
        if ( dishEntity == null ) {
            return null;
        }

        DishResponseDto dishResponseDto = new DishResponseDto();

        dishResponseDto.setName( dishEntity.getName() );
        dishResponseDto.setPrice( dishEntity.getPrice() );
        dishResponseDto.setDescription( dishEntity.getDescription() );
        dishResponseDto.setUrlImage( dishEntity.getUrlImage() );
        dishResponseDto.setCategory( dishEntity.getCategory() );
        dishResponseDto.setCampus( dishEntity.getCampus() );
        dishResponseDto.setActive( dishEntity.isActive() );

        return dishResponseDto;
    }

    @Override
    public List<DishResponseDto> entitiesToResponses(List<DishEntity> dishesEntities) {
        if ( dishesEntities == null ) {
            return null;
        }

        List<DishResponseDto> list = new ArrayList<DishResponseDto>( dishesEntities.size() );
        for ( DishEntity dishEntity : dishesEntities ) {
            list.add( entityToResponse( dishEntity ) );
        }

        return list;
    }

    @Override
    public DishEntity responseToEntity(DishResponseDto dishResponseDto) {
        if ( dishResponseDto == null ) {
            return null;
        }

        DishEntity dishEntity = new DishEntity();

        dishEntity.setName( dishResponseDto.getName() );
        dishEntity.setPrice( dishResponseDto.getPrice() );
        dishEntity.setDescription( dishResponseDto.getDescription() );
        dishEntity.setUrlImage( dishResponseDto.getUrlImage() );
        dishEntity.setCategory( dishResponseDto.getCategory() );
        dishEntity.setActive( dishResponseDto.isActive() );
        dishEntity.setCampus( dishResponseDto.getCampus() );

        return dishEntity;
    }

    @Override
    public List<DishEntity> responsesToEntities(List<DishResponseDto> dishesResponseDto) {
        if ( dishesResponseDto == null ) {
            return null;
        }

        List<DishEntity> list = new ArrayList<DishEntity>( dishesResponseDto.size() );
        for ( DishResponseDto dishResponseDto : dishesResponseDto ) {
            list.add( responseToEntity( dishResponseDto ) );
        }

        return list;
    }

    private Long dishEntityCategoryId(DishEntity dishEntity) {
        if ( dishEntity == null ) {
            return null;
        }
        CategoryEntity category = dishEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long dishEntityCampusId(DishEntity dishEntity) {
        if ( dishEntity == null ) {
            return null;
        }
        CampusEntity campus = dishEntity.getCampus();
        if ( campus == null ) {
            return null;
        }
        Long id = campus.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected CategoryEntity dishRequestDtoToCategoryEntity(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( dishRequestDto.getCategory() );

        return categoryEntity;
    }

    protected CampusEntity dishRequestDtoToCampusEntity(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        CampusEntity campusEntity = new CampusEntity();

        campusEntity.setId( dishRequestDto.getCampus() );

        return campusEntity;
    }
}
