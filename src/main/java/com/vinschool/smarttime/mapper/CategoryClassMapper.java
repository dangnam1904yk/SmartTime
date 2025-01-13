package com.vinschool.smarttime.mapper;

import org.mapstruct.Mapper;

import com.vinschool.smarttime.entity.CategoryClass;
import com.vinschool.smarttime.model.dto.CategoryClassDto;

@Mapper(componentModel = "spring")
public interface CategoryClassMapper {
    CategoryClassDto toCategoryClassDto(CategoryClass categoryClass);
}
