package com.vinschool.smarttime.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;

@Mapper(componentModel = "spring")
public interface TimeSheetMapper {

    TimeSheetResponsive toTimeSheetResponsive(TimeSheet timeSheet);

    List<TimeSheetResponsive> toListTimeSheetResponsive(List<TimeSheet> timeSheets);
}
