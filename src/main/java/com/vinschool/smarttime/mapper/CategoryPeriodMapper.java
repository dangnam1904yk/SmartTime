package com.vinschool.smarttime.mapper;

import org.mapstruct.Mapper;

import com.vinschool.smarttime.entity.CategoryPeriod;
import com.vinschool.smarttime.model.dto.CategoryPeriodDto;

@Mapper(componentModel = "spring")
public interface CategoryPeriodMapper {
    CategoryPeriodDto toCategoryPeriodDto(CategoryPeriod categoryPeriod);
}
