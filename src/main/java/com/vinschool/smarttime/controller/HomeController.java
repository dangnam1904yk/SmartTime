package com.vinschool.smarttime.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinschool.smarttime.entity.DetailNotification;
import com.vinschool.smarttime.entity.Notification;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.model.response.TimeSheetChekNotification;
import com.vinschool.smarttime.repository.DetailNotificationRepository;
import com.vinschool.smarttime.repository.NotificationRepository;
import com.vinschool.smarttime.service.RoleService;
import com.vinschool.smarttime.service.TimeSheetService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.Constant;
import com.vinschool.smarttime.ulti.HashPassword;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TimeSheetService timeSheetService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private DetailNotificationRepository detailNotificationRepository;

    @GetMapping("/")
    public String Index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        model.addAttribute("user", user.getId());
        session.setAttribute("sesionUser", user);
        if (user.getEmail().startsWith("admin")) {
            model.addAttribute("countNoti", detailNotificationRepository.count());
        } else {
            model.addAttribute("countNoti", detailNotificationRepository.countByUserId(user.getId()));
        }
        return "page/index";
    }

    @GetMapping("/dang-ky")
    public String FormRegister(Model model) {
        return "page/register";
    }

    @PostMapping("/dang-ky")
    public String Register(Model model, @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("fullName") String fullName) {
        User user = new User();
        user.setActive(true);
        user.setCreateDate(new Date());
        user.setDisplayName(fullName);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(password);
        // roleService.findByCode("GIAOVIEN");
        User user2 = userService.save(user, "GIAOVIEN");
        if (user2 == null) {
            model.addAttribute("error", "Email đã tồn tại");
        } else {
            model.addAttribute("sucess", "Đăng ký thành công");
        }
        return "page/register";
    }

    @GetMapping("/dang-nhap")
    public String FormLogin(Model model) {
        return "page/login";
    }

    @PostMapping("/dang-nhap")
    public String Login(Model model, @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = userService.finByEmail(email);
        if (user == null) {
            model.addAttribute("error", "Đăng nhập thất bại");
            return "page/login";
        }
        boolean check = HashPassword.checkPass(password, user.getPassword());

        if (check == true) {
            session.setAttribute("user", user);
            List<String> role = new ArrayList<String>();
            for (int i = 0; i < user.getRole().size(); i++) {
                role.add(user.getRole().get(i).getCodeRole());
            }
            session.setAttribute("sesionRole", role);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Đăng nhập thất bại");
        }
        return "page/login";
    }

    @GetMapping("/dang-xuat")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
