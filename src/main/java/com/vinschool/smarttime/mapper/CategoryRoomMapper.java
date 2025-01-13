package com.vinschool.smarttime.mapper;

import org.mapstruct.Mapper;

import com.vinschool.smarttime.entity.CategoryRoom;
import com.vinschool.smarttime.model.dto.CategoryRoomDto;

@Mapper(componentModel = "spring")
public interface CategoryRoomMapper {
    CategoryRoomDto t0CategoryRoomDto(CategoryRoom categoryRoom);
}
