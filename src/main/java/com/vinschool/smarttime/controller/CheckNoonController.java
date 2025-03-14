package com.vinschool.smarttime.controller;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinschool.smarttime.entity.CheckNoon;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.model.response.CheckNoonResponsive;
import com.vinschool.smarttime.service.CheckNoonService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.Constant;
import com.vinschool.smarttime.ulti.SecurityUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CheckNoonController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckNoonService checkNoonService;

    @GetMapping("/trong-trua")
    public String CreatDate(Model model) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        if (userPrincipal.getAuthorities().contains(Constant.ROLE.ADMIN)) {
            model.addAttribute("list",
                    checkNoonService.findByUserIdCreateAndTimeLine(userPrincipal.getUser().getId(), "2025-01"));
        } else {
            model.addAttribute("list",
                    checkNoonService.findByUserIdWorkAndTimeLine(userPrincipal.getUser().getId(), "2025-01"));

        }
        model.addAttribute("listUser", userService.findUserByCodeRole(Constant.ROLE.GIAO_VIEN));

        return "page/checknoon/list";
    }

    @GetMapping("/lap-lich-trong-trua")
    public String FormLapLich(Model model, HttpServletRequest request) {
        model.addAttribute("users", userService.findUserByCodeRole(Constant.ROLE.GIAO_VIEN));
        return "page/checknoon/phan-cong";
    }

    @PostMapping("/lap-lich-trong-trua")
    public String CreateTimeLine(@RequestParam("data") String data, @RequestParam("timeLine") String timeLine,
            HttpServletRequest request) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        checkNoonService.LuuPhanCongLich(data, timeLine, userPrincipal);
        return "page/checknoon/list";
    }

    @PostMapping("/cham-cong")
    public String ChamCong(@RequestParam("data") String data) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        checkNoonService.ChamCong(data, userPrincipal);
        return "redirect:/trong-trua";
    }

}
