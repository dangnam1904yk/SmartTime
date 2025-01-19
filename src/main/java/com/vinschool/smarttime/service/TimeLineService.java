package com.vinschool.smarttime.service;

import java.time.LocalDate;
import java.util.List;

import com.vinschool.smarttime.entity.TimeLine;
import com.vinschool.smarttime.model.dto.TimeLineDto;

public interface TimeLineService {
    TimeLine save(TimeLine timeLine);

    void detele(String id);

    List<TimeLine> getAll();

    TimeLine Detail(String id);

    List<TimeLine> findByType(int type);

    List<TimeLineDto> findTimeLineByType(int type);

    List<TimeLine> findByEndDateAfterAndType(LocalDate date, int type);
}
