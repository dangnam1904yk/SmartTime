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
            // Tạo JobKey và TriggerKey
            JobKey jobKey = new JobKey("notificationJob_" + accountId); // Tên job duy nhất
            TriggerKey triggerKey = new TriggerKey("notificationTrigger_" + accountId); // Tên trigger duy nhất

            // Kiểm tra xem job đã tồn tại chưa
            if (scheduler.checkExists(jobKey)) {
                System.out.println("Job đã tồn tại cho tài khoản: " + accountId);
                // Có thể update Trigger hoặc xử lý theo yêu cầu của bạn
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)) // Lập lịch
                        .build();

                scheduler.rescheduleJob(triggerKey, trigger); // Cập nhật lại trigger
                System.out.println("Lịch trình đã được cập nhật cho tài khoản: " + accountId);
            } else {
                // Tạo JobDetail mới
                JobDetail jobDetail = JobBuilder.newJob(NotificationJob.class)
                        .withIdentity(jobKey) // ID job
                        .usingJobData("accountId", accountId) // Dữ liệu tài khoản
                        .usingJobData("message", message) // Dữ liệu thông báo
                        .build();

                // Tạo Trigger mới với cron expression
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey) // ID trigger
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)) // Lập lịch
                        .build();

                // Lập lịch job với trigger
                scheduler.scheduleJob(jobDetail, trigger);

                System.out.println("Lịch trình đã được tạo cho tài khoản: " + accountId);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            System.out.println("Không thể tạo lịch trình cho tài khoản: " + accountId);
        }
    }
}
