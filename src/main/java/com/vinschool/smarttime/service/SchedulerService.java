package com.vinschool.smarttime.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinschool.smarttime.config.NotificationJob;

@Service
public class SchedulerService {

    @Autowired
    private Scheduler scheduler;

    // Phương thức để tạo lịch trình cho một tài khoản
    public void scheduleNotification(String accountId, String message, String cronExpression) {
        try {
            // Tạo JobDetail
            JobDetail jobDetail = JobBuilder.newJob(NotificationJob.class)
                    .withIdentity("notificationJob_" + accountId) // ID job
                    .usingJobData("accountId", accountId) // Dữ liệu tài khoản
                    .usingJobData("message", message) // Dữ liệu thông báo
                    .build();

            // Tạo Trigger với cron expression
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("notificationTrigger_" + accountId) // ID trigger
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)) // Lập lịch
                    .build();

            // Lập lịch job với trigger
            scheduler.scheduleJob(jobDetail, trigger);

            System.out.println("Lịch trình đã được tạo cho tài khoản: " + accountId);
        } catch (SchedulerException e) {
            e.printStackTrace();
            System.out.println("Không thể tạo lịch trình cho tài khoản: " + accountId);
        }
    }
}
