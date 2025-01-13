package com.vinschool.smarttime.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinschool.smarttime.entity.TimeLine;
import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.model.request.CheckNoonRequest;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;
import com.vinschool.smarttime.repository.TimeSheetRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public TimeSheet save(TimeSheet timeSheet, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return null;
        return timeSheetRepository.save(timeSheet);
    }

    @Override
    public TimeSheet detail(String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return null;
        Optional<TimeSheet> optional = timeSheetRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    @Override
    public void delete(String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            timeSheetRepository.deleteById(id);
        }
    }

    @Override
    public void saveAll(List<TimeSheet> timIterator, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            timeSheetRepository.saveAll(timIterator);
        }
    }

    @Override
    public void createTimeSheet(String dataTimeSheet, String dataTimeLine, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
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
                timeLineDb.setMonth(map.get("month").toString());
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
                    timSheet.setCreateBy(user.getId());
                    timSheet.setCreateDate(new Date());
                    timSheet.setUser(userService.findById(item.get("user_id").toString()));
                    timSheet.setCategoryClass(categoryClassService.getById(item.get("category_class_id").toString()));
                    timSheet.setCategoryPeriod(categoryPeriodService.detail(item.get("category_period_id").toString()));
                    timSheet.setCategorySubject(
                            categorySubjectService.detail(item.get("category_subject_id").toString()));
                    timSheet.setCategoryRoom(categoryRoomSerive.detail(item.get("category_room_id").toString()));
                    timSheet.setNameTimeSheet(item.get("nameTimeSheet").toString());
                    timSheet.setTimeLine(timeLineSave);
                    list.add(timSheet);
                }

                timeLineDb.setId(timeLineSave.getId());
                timeLineDb.setTimeSheet(list);
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

}
