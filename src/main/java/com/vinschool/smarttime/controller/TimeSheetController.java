package com.vinschool.smarttime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinschool.smarttime.entity.CategoryPeriod;
import com.vinschool.smarttime.entity.CategoryRoom;
import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.service.CategoryClassService;
import com.vinschool.smarttime.service.CategoryPeriodService;
import com.vinschool.smarttime.service.CategoryRoomSerive;
import com.vinschool.smarttime.service.CategorySubjectService;
import com.vinschool.smarttime.service.TimeSheetService;
import com.vinschool.smarttime.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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

    @GetMapping("/lap-thoi-khoa-bieu")
    public String FormLapThoiKhoaBieu(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        model.addAttribute("class", categoryClassService.getAll());
        model.addAttribute("rooms", categoryRoomSerive.getAll());
        model.addAttribute("period", categoryPeriodService.getAll());
        model.addAttribute("subjects", categorySubjectService.getAll());
        model.addAttribute("user", userService.getAll());
        return "page/time-sheet/form";
    }

    @PostMapping("/lap-thoi-khoa-bieu")
    public String CreateTimeSheet(Model model, HttpServletRequest request,
            @RequestParam("data") String dataTimeSheetStr,
            @RequestParam("timeLine") String dataTimeLine) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        timeSheetService.createTimeSheet(dataTimeSheetStr, dataTimeLine, request);
        return "redirect:/lap-thoi-khoa-bieu";
    }
}
