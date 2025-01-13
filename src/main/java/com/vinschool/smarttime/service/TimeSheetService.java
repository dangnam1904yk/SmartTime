package com.vinschool.smarttime.service;

import java.util.Iterator;
import java.util.List;

import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;

import jakarta.servlet.http.HttpServletRequest;

public interface TimeSheetService {
    TimeSheet save(TimeSheet timeSheet, HttpServletRequest request);

    TimeSheet detail(String id, HttpServletRequest request);

    void delete(String id, HttpServletRequest request);

    void saveAll(List<TimeSheet> timIterator, HttpServletRequest request);

    void createTimeSheet(String dataTimeSheet, String dataTimeLine, HttpServletRequest request);

    List<TimeSheet> findByTimeLineId(String timLineId);

    List<TimeSheet> findByUserIdTimeLineId(String userId, String timLineId);

    List<TimeSheetResponsive> findTimeSheetByIdTimeLine(String id);

    List<TimeSheetResponsive> findTimeSheetByIdTimeLineAndUserIdTeach(String timLineId, String userId);

    List<TimeSheetResponsive> findTimeSheetByIdTimeLineWithNoteBook(String timLineId);

    List<TimeSheetResponsive> findTimeSheetByIdTimeLineAndUserIdTeachWithNoteBook(String timLineId, String userid);

    List<TimeSheetResponsive> findTimeSheetByUserIdTeachWithNoteBook(String userId);

    List<TimeSheetResponsive> findTimeSheetWithNoteBook();

}
