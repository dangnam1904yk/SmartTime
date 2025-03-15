package com.vinschool.smarttime.service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinschool.smarttime.entity.TimeLine;
import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.model.response.TimeSheetChekNotification;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;
import com.vinschool.smarttime.repository.TimeSheetRepository;
import com.vinschool.smarttime.ulti.Constant;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeSheetServiceImpl implements TimeSheetService {

    private final TimeSheetRepository timeSheetRepository;
    private final TimeLineService timeLineService;
    private final UserService userService;
    private final CategoryClassService categoryClassService;
    private final CategoryPeriodService categoryPeriodService;
    private final CategoryRoomSerive categoryRoomSerive;
    private final CategorySubjectService categorySubjectService;

    @Override
    public TimeSheet save(TimeSheet timeSheet) {
        return timeSheetRepository.save(timeSheet);
    }

    @Override
    public TimeSheet detail(String id) {
        Optional<TimeSheet> optional = timeSheetRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    @Override
    public void delete(String id) {
        timeSheetRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<TimeSheet> timIterator) {
        timeSheetRepository.saveAll(timIterator);
    }

    @Override
    public void createTimeSheet(String dataTimeSheet, String dataTimeLine, UserPrincipal user) {
        if (user != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            TimeLine timeLineDb = new TimeLine();
            List<Map<String, Object>> dataList;
            try {
                Map<String, Object> map = objectMapper.readValue(dataTimeLine, new TypeReference<>() {
                });
                ;

                timeLineDb.setActive(
                        map.get("isActive") != null ? map.get("isActive").toString() == "true" ? true : false : false);
                if (map.get("calendarType").toString().equals("1")) {
                    // 2025-W04
                    if (map.get("weekStart") != null) {
                        String[] split = map.get("weekStart").toString().split("-W");
                        LocalDate firstDayOfYear = LocalDate.of(Integer.parseInt(split[0]), 1, 1);

                        WeekFields weekFields = WeekFields.of(Locale.getDefault());

                        LocalDate startOfWeek = firstDayOfYear
                                .with(weekFields.weekOfYear(), Integer.parseInt(split[1])) // Đặt tuần
                                .with(weekFields.dayOfWeek(), 1); // Đặt ngày đầu tuần (thứ Hai)
                        LocalDate endOfWeek = startOfWeek.plusDays(6);
                        timeLineDb.setStartDate(startOfWeek);
                        timeLineDb.setEndDate(endOfWeek);

                    }
                    timeLineDb.setCalendarType(Integer.parseInt(map.get("calendarType").toString()));
                }
                if (map.get("moth") != null) {
                    timeLineDb.setMonth(map.get("month").toString());
                }
                if (map.get("nameTimeLine") != null) {
                    timeLineDb.setNameTimeLine(map.get("nameTimeLine").toString());
                }
                timeLineDb.setType(Constant.TYPE_TIME_LINE.SO_DAU_BAI);
                TimeLine timeLineSave = timeLineService.save(timeLineDb);

                dataList = objectMapper.readValue(dataTimeSheet, new TypeReference<>() {
                });
                List<TimeSheet> list = new ArrayList<>();
                for (Map<String, Object> item : dataList) {

                    TimeSheet timSheet = new TimeSheet();
                    if (item.get("id") == null) {
                        timSheet.setId(null);
                    } else {
                        timSheet.setId(item.get("id").toString());
                    }

                    timSheet.setThu(item.get("thu").toString());
                    timSheet.setCreateBy(user.getUser().getId());
                    timSheet.setCreateDate(new Date());
                    timSheet.setUser(userService.findById(item.get("userId").toString()));
                    timSheet.setCategoryClass(categoryClassService.getById(item.get("categoryClassId").toString()));
                    timSheet.setCategoryPeriod(categoryPeriodService.detail(item.get("categoryPeriodId").toString()));
                    timSheet.setCategorySubject(
                            categorySubjectService.detail(item.get("categorySubjectId").toString()));
                    timSheet.setCategoryRoom(categoryRoomSerive.detail(item.get("categoryRoomId").toString()));
                    timSheet.setNameTimeSheet(item.get("nameTimeSheet").toString());
                    timSheet.setTimeLine(timeLineSave);
                    list.add(timSheet);
                }

                timeLineDb.setId(timeLineSave.getId());
                // timeLineDb.setTimeSheet(list);
                timeSheetRepository.saveAll(list);
            } catch (JsonMappingException e) {

                e.printStackTrace();
            } catch (JsonProcessingException e) {

                e.printStackTrace();
            }

        }
    }

    @Override
    public List<TimeSheet> findByTimeLineId(String timLineId) {
        return timeSheetRepository.findByTimeLineId(timLineId);
    }

    @Override
    public List<TimeSheet> findByUserIdTimeLineId(String userId, String timLineId) {
        return timeSheetRepository.findByUserIdAndTimeLineId(userId, timLineId);
    }

    @Override
    public List<TimeSheetResponsive> findTimeSheetByIdTimeLine(String id) {
        return timeSheetRepository.findTimeSheetByIdTimeLine(id);
    }

    @Override
    public List<TimeSheetResponsive> findTimeSheetByIdTimeLineAndUserIdTeach(String timLineId, String userId) {
        return timeSheetRepository.findTimeSheetByIdTimeLineAndUserIdTeach(timLineId, userId);
    }

    @Override
    public List<TimeSheetResponsive> findTimeSheetByIdTimeLineWithNoteBook(String timLineId) {
        return timeSheetRepository.findTimeSheetByIdTimeLineWithNoteBook(timLineId);
    }

    @Override
    public List<TimeSheetResponsive> findTimeSheetByIdTimeLineAndUserIdTeachWithNoteBook(String timLineId,
            String userid) {
        return timeSheetRepository.findTimeSheetByIdTimeLineAndUserIdTeachWithNoteBook(timLineId, userid);
    }

    @Override
    public List<TimeSheetResponsive> findTimeSheetByUserIdTeachWithNoteBook(String userId) {
        return timeSheetRepository.findTimeSheetByUserIdTeachWithNoteBook(userId);
    }

    @Override
    public List<TimeSheetResponsive> findTimeSheetWithNoteBook() {
        return timeSheetRepository.findTimeSheetWithNoteBook();
    }

    @Override
    public TimeSheetResponsive findNoteBookByIdWWithTimeSheet(String id) {
        return timeSheetRepository.findNoteBookByIdWWithTimeSheet(id);
    }

    @Override
    public List<TimeSheet> getAll() {
        return timeSheetRepository.findAll();
    }

    @Override
    public List<TimeSheetResponsive> getAllTimeSheetResponsive() {
        return timeSheetRepository.getAllTimeSheetResponsive();
    }

    @Override
    public List<TimeSheetResponsive> getTimeSheetResponsiveByUserIdTeach(String id) {
        return timeSheetRepository.getTimeSheetResponsiveByUserIdTeach(id);
    }

    @Override
    public List<TimeSheetChekNotification> CheckSoDauBai(LocalDate dateCheck, Boolean isActive, Boolean isTrain,
            String thu, String userId) {
        return timeSheetRepository.CheckSoDauBai(dateCheck, isActive, isTrain, thu, userId);
    }
}
