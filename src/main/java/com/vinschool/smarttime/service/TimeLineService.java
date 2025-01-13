package com.vinschool.smarttime.service;

import java.util.List;

import com.vinschool.smarttime.entity.TimeLine;

public interface TimeLineService {
    TimeLine save(TimeLine timeLine);

    void detele(String id);

    List<TimeLine> getAll();

    TimeLine Detail(String id);

    List<TimeLine> findByType(int type);
}
