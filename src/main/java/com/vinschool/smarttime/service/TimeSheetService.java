package com.vinschool.smarttime.service;

import java.time.LocalDate;
import java.util.List;

import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.model.response.TimeSheetChekNotification;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;

public interface TimeSheetService {
    TimeSheet save(TimeSheet timeSheet);

    TimeSheet detail(String id);

    void delete(String id);

    void saveAll(List<TimeSheet> timIterator);

    void createTimeSheet(String dataTimeSheet, String dataTimeLine, UserPrincipal user);

    List<TimeSheet> findByTimeLineId(String timLineId);

    List<TimeSheet> findByUserIdTimeLineId(String userId, String timLineId);

    List<TimeSheetResponsive> findTimeSheetByIdTimeLine(String id);

    List<TimeSheetResponsive> findTimeSheetByIdTimeLineAndUserIdTeach(String timLineId, String userId);

    List<TimeSheetResponsive> findTimeSheetByIdTimeLineWithNoteBook(String timLineId);

    List<TimeSheetResponsive> findTimeSheetByIdTimeLineAndUserIdTeachWithNoteBook(String timLineId, String userid);

    List<TimeSheetResponsive> findTimeSheetByUserIdTeachWithNoteBook(String userId);

    List<TimeSheetResponsive> findTimeSheetWithNoteBook();

    TimeSheetResponsive findNoteBookByIdWWithTimeSheet(String id);

    List<TimeSheet> getAll();

    List<TimeSheetResponsive> getAllTimeSheetResponsive();

    List<TimeSheetResponsive> getTimeSheetResponsiveByUserIdTeach(String id);

    List<TimeSheetChekNotification> CheckSoDauBai(LocalDate dateCheck, Boolean isActive, Boolean isTrain, String thu,
            String userId);

}
