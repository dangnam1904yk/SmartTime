package com.vinschool.smarttime.service;

import java.util.Iterator;
import java.util.List;

import com.vinschool.smarttime.entity.TimeSheet;

import jakarta.servlet.http.HttpServletRequest;

public interface TimeSheetService {
    TimeSheet save(TimeSheet timeSheet, HttpServletRequest request);

    TimeSheet detail(String id, HttpServletRequest request);

    void delete(String id, HttpServletRequest request);

    void saveAll(List<TimeSheet> timIterator, HttpServletRequest request);

    void createTimeSheet(String dataTimeSheet, String dataTimeLine, HttpServletRequest request);
}
