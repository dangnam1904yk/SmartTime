package com.vinschool.smarttime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinschool.smarttime.entity.AccountSchedule;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.repository.AccountScheduleRepository;
import com.vinschool.smarttime.service.SchedulerService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.SecurityUtils;

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
    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String getMethodName(HttpServletRequest request, Model model) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        AccountSchedule schedule = scheduleRepository.findByUserId(userPrincipal.getUser().getId());
        if (schedule != null) {
            model.addAttribute("cron",
                    scheduleRepository.findByUserId(userPrincipal.getUser().getId()).getCronExpression());
        } else {
            model.addAttribute("cron", "");
        }
        return "page/cron/form";
    }

    @PostMapping("/cron")
    public String SaveCron(HttpServletRequest request, Model model, @RequestParam("cron") String cron) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        AccountSchedule accountSchedule = scheduleRepository.findByUserId(userPrincipal.getUser().getId());
        if (accountSchedule == null) {
            accountSchedule = new AccountSchedule();
        }
        accountSchedule.setUser(userService.findById(userPrincipal.getUser().getId()));
        accountSchedule.setCronExpression(cron);
        scheduleRepository.save(accountSchedule);
        schedulerService.scheduleNotification(userPrincipal.getUser().getId(), userPrincipal.getUser().getEmail(),
                cron);
        model.addAttribute("succes", "Cập nhật thành công");
        return "page/cron/form";
    }

}
