package com.vinschool.smarttime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationScheduler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // @Scheduled(cron = "0 0 12,17 * * ?") // Chạy lúc 12h và 17h
    @Scheduled(fixedRate = 5000)
    public void sendScheduledNotifications() {
        String message = "Thông báo: Đây là thông báo được gửi vào lúc 12h hoặc 17h.";
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
