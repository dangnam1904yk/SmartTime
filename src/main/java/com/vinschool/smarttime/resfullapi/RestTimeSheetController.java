package com.vinschool.smarttime.resfullapi;

import org.springframework.web.bind.annotation.RestController;

import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.mapper.TimeSheetMapper;
import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;
import com.vinschool.smarttime.service.TimeLineService;
import com.vinschool.smarttime.service.TimeSheetService;
import com.vinschool.smarttime.ulti.SecurityUtils;
import com.vinschool.smarttime.ulti.Constant.ROLE;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class RestTimeSheetController {

    private final TimeSheetService timeSheetService;
    private final TimeSheetMapper timeSheetMapper;

    @GetMapping("/api/timeSheet/getTimeSheetByTimeLine")
    public List<TimeSheetResponsive> getTimeSheetByTimeLine(@RequestParam("id") String param) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        List<TimeSheetResponsive> result = new ArrayList<>();
        if (userPrincipal.getAuthorities().contains(ROLE.ADMIN)) {

            result = timeSheetService.findTimeSheetByIdTimeLine(param);

        } else {

            result = timeSheetService.findTimeSheetByIdTimeLineAndUserIdTeach(param, userPrincipal.getUser().getId());
        }
        return result;
    }

}
