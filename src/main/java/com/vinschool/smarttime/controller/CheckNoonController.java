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
import com.vinschool.smarttime.model.response.CheckNoonResponsive;
import com.vinschool.smarttime.service.CheckNoonService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.Constant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CheckNoonController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckNoonService checkNoonService;

    @GetMapping("/trong-trua")
    public String CreatDate(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";

        if (user.getEmail().toLowerCase().startsWith("admin")) {
            model.addAttribute("list", checkNoonService.findByUserIdCreateAndTimeLine(user.getId(), "2025-01"));
        } else {
            model.addAttribute("list", checkNoonService.findByUserIdWorkAndTimeLine(user.getId(), "2025-01"));

        }
        model.addAttribute("listUser", userService.findUserByCodeRole(Constant.ROLE.GIAO_VIEN));

        return "page/checknoon/list";
    }

    @GetMapping("/lap-lich-trong-trua")
    public String FormLapLich(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";

        model.addAttribute("users", userService.findUserByCodeRole(Constant.ROLE.GIAO_VIEN));
        return "page/checknoon/phan-cong";
    }

    @PostMapping("/lap-lich-trong-trua")
    public String CreateTimeLine(@RequestParam("data") String data, @RequestParam("timeLine") String timeLine,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        checkNoonService.LuuPhanCongLich(data, timeLine, request);
        return "page/checknoon/list";
    }

    @PostMapping("/cham-cong")
    public String ChamCong(@RequestParam("data") String data, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";

        checkNoonService.ChamCong(data, request);
        return "redirect:/trong-trua";
    }

}
