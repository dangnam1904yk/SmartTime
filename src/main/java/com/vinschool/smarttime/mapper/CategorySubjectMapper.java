package com.vinschool.smarttime.mapper;

import org.mapstruct.Mapper;

import com.vinschool.smarttime.entity.CategorySubject;
import com.vinschool.smarttime.model.dto.CategorySubjectDto;

@Mapper(componentModel = "spring")
public interface CategorySubjectMapper {
    CategorySubjectDto toCategorySubjectDto(CategorySubject categorySubject);
}
