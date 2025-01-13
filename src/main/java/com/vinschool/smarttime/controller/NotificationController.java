package com.vinschool.smarttime.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/send") // Lắng nghe các message từ client gửi đến /app/send
    @SendTo("/topic/notifications") // Đẩy thông báo đến các client lắng nghe /topic/notifications
    public String sendNotification(String message) {
        return message; // Nội dung thông báo sẽ được trả về cho client
    }
}
