package com.vinschool.smarttime.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeLineDto {
    String id;
    String month;
    boolean isActive;
    int type;
    String nameTimeLine;
    LocalDate startDate;
    LocalDate endDate;
    int calendarType;
}
