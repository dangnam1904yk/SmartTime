package com.vinschool.smarttime.mapper;

import org.mapstruct.Mapper;

import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.model.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
