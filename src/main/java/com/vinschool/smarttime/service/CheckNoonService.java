package com.vinschool.smarttime.service;

import java.util.List;

import com.vinschool.smarttime.entity.CheckNoon;
import com.vinschool.smarttime.entity.TimeLine;
import com.vinschool.smarttime.model.request.CheckNoonRequest;
import com.vinschool.smarttime.model.response.CheckNoonResponsive;

import jakarta.servlet.http.HttpServletRequest;

public interface CheckNoonService {
    void save(CheckNoon checkNoon);

    void delete(String id);

    List<CheckNoon> getAll();

    void LuuPhanCongLich(String checkNone, String timeLine, HttpServletRequest servletRequest);

    List<CheckNoon> findByUserIdWorkAndTimeLine(String uesrid, String month);

    List<CheckNoon> findByUserIdCreateAndTimeLine(String userid, String month);

    List<CheckNoonResponsive> findByUserIdWorkAndTimeLineDto(String uesrid, String month);

    List<CheckNoonResponsive> findByUserIdCreateAndTimeLineDto(String userid, String month);

    // List<CheckNoonResponsive> findByUserIdCreateAndTimeLineDtoNameJDPC(String
    // userid, String month);

    void ChamCong(String data, HttpServletRequest request);

}
