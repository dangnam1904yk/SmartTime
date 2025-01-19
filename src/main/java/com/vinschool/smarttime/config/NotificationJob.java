package com.vinschool.smarttime.config;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.vinschool.smarttime.service.NotificationService;
import com.vinschool.smarttime.service.UserService;

public class NotificationJob implements Job {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Lấy dữ liệu từ JobDataMap
        String accountId = context.getJobDetail().getJobDataMap().getString("accountId");
        String message = context.getJobDetail().getJobDataMap().getString("message");

        // Gọi đến hàm bạn muốn thực thi
        NotificationMessages(message);
        // Lấy NotificationService từ ApplicationContext
        NotificationService notificationService = SpringContext.getBean(NotificationService.class);

        // Gọi hàm xử lý trong service
        notificationService.sendScheduledNotifications(accountId, message);

        System.out.println("Chạy cho tài khoản: " + accountId + " với thông báo: " + message);
    }

    // Hàm cần chạy
    private void NotificationMessages(String data) {
        System.out.println("Thông báo: " + data);
    }

    public void sendScheduledNotifications() {
        String message = "Thông báo: Đây là thông báo được gửi vào lúc 12h hoặc 17h.";
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
