package com.vinschool.smarttime.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vinschool.smarttime.entity.CheckNoon;
import com.vinschool.smarttime.model.request.CheckNoonRequest;

@Mapper(componentModel = "spring")
public interface CheckNoonMapper {

    CheckNoon toCheckNoone(CheckNoonRequest request);

    List<CheckNoon> toListCheckNoone(List<CheckNoonRequest> request);
}
