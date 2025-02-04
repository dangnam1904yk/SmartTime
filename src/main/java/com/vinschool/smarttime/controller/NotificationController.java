package com.vinschool.smarttime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.repository.DetailNotificationRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class NotificationController {

    @MessageMapping("/send") // Lắng nghe các message từ client gửi đến /app/send
    @SendTo("/topic/notifications") // Đẩy thông báo đến các client lắng nghe /topic/notifications
    public String sendNotification(String message) {
        return message; // Nội dung thông báo sẽ được trả về cho client
    }

    @Autowired
    private DetailNotificationRepository detailNotificationRepository;

    @GetMapping("/danh-sach-thong-bao")
    public String getMethodName(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        if (user.getEmail().startsWith("admin")) {

            model.addAttribute("list", detailNotificationRepository.findAll());
        } else {
            model.addAttribute("list", detailNotificationRepository.findByUserId(user.getId()));
        }
        return "page/notification/list";
    }
}
