package com.vinschool.smarttime.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;
import com.vinschool.smarttime.service.CategoryClassService;
import com.vinschool.smarttime.service.CategoryPeriodService;
import com.vinschool.smarttime.service.CategoryRoomSerive;
import com.vinschool.smarttime.service.CategorySubjectService;
import com.vinschool.smarttime.service.TimeLineService;
import com.vinschool.smarttime.service.TimeSheetService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.Constant;
import com.vinschool.smarttime.ulti.Constant.ROLE;
import com.vinschool.smarttime.ulti.SecurityUtils;

@Controller
public class TimeSheetController {

    @Autowired
    private CategoryClassService categoryClassService;
    @Autowired
    private CategoryRoomSerive categoryRoomSerive;
    @Autowired
    private CategoryPeriodService categoryPeriodService;
    @Autowired
    private CategorySubjectService categorySubjectService;
    @Autowired
    private UserService userService;

    @Autowired
    private TimeSheetService timeSheetService;

    @Autowired
    private TimeLineService timeLineService;

    @GetMapping("/lap-thoi-khoa-bieu")
    public String FormLapThoiKhoaBieu(Model model) {
        model.addAttribute("class", categoryClassService.getAll());
        model.addAttribute("rooms", categoryRoomSerive.getAll());
        model.addAttribute("period", categoryPeriodService.getAll());
        model.addAttribute("subjects", categorySubjectService.getAll());
        model.addAttribute("user", userService.findUserByCodeRole(Constant.ROLE.GIAO_VIEN));
        return "page/time-sheet/form";
    }

    @PostMapping("/lap-thoi-khoa-bieu")
    public String CreateTimeSheet(Model model,
            @RequestParam("data") String dataTimeSheetStr,
            @RequestParam("timeLine") String dataTimeLine) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        timeSheetService.createTimeSheet(dataTimeSheetStr, dataTimeLine, userPrincipal);
        return "redirect:/lap-thoi-khoa-bieu";
    }

    @GetMapping("/thoi-khoa-bieu")
    public String ThoiKhoaBieu(Model model,
            @RequestParam(name = "timeLine", required = false) String timeLine,
            @RequestParam(name = "giaovien", required = false) String userId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        List<TimeSheetResponsive> list = new ArrayList<>();

        if (userPrincipal.getAuthorities().contains(ROLE.ADMIN)) {
            if (timeLine == null || timeLine.equals("")) {
                list = timeSheetService.getAllTimeSheetResponsive();
            } else {

                list = timeSheetService.findTimeSheetByIdTimeLine(timeLine);
            }
        } else {
            if (timeLine == null || timeLine.equals("")) {
                list = timeSheetService.getTimeSheetResponsiveByUserIdTeach(userPrincipal.getUser().getId());
            } else {
                list = timeSheetService.findTimeSheetByIdTimeLineAndUserIdTeach(timeLine,
                        userPrincipal.getUser().getId());
            }
        }
        model.addAttribute("timeSheets", list);
        model.addAttribute("userLogin", userPrincipal.getUser());
        model.addAttribute("timeLineSearch", timeLine);
        model.addAttribute("userSearch", userId);
        model.addAttribute("users", userService.findUserByCodeRole(Constant.ROLE.GIAO_VIEN));
        model.addAttribute("timeLine", timeLineService.findTimeLineByType(Constant.TYPE_TIME_LINE.SO_DAU_BAI));
        return "page/time-sheet/list";
    }

}
