package com.vinschool.smarttime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinschool.smarttime.entity.AccountSchedule;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.repository.AccountScheduleRepository;
import com.vinschool.smarttime.service.SchedulerService;
import com.vinschool.smarttime.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountScheduleController {

    @Autowired
    private AccountScheduleRepository scheduleRepository;

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private UserService userService;

    @GetMapping("/cron")
    public String getMethodName(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        AccountSchedule schedule = scheduleRepository.findByUserId(user.getId());
        if (schedule != null) {
            model.addAttribute("cron", scheduleRepository.findByUserId(user.getId()).getCronExpression());
        } else {
            model.addAttribute("cron", "");
        }
        return "page/cron/form";
    }

    @PostMapping("/cron")
    public String SaveCron(HttpServletRequest request, Model model, @RequestParam("cron") String cron) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        AccountSchedule accountSchedule = scheduleRepository.findByUserId(user.getId());
        if (accountSchedule == null) {
            accountSchedule = new AccountSchedule();
        }
        accountSchedule.setUser(userService.findById(user.getId()));
        accountSchedule.setCronExpression(cron);
        scheduleRepository.save(accountSchedule);
        schedulerService.scheduleNotification(user.getId(), user.getEmail(), cron);
        model.addAttribute("succes", "Cập nhật thành công");
        return "page/cron/form";
    }

}
