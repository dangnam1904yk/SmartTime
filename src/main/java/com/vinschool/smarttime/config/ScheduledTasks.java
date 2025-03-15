package com.vinschool.smarttime.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    // Lập lịch chạy mỗi 5 giây
    @Scheduled(fixedRate = 5000)
    public void runEveryFiveSeconds() {
        // System.out.println("Chạy mỗi 5 giây: " + System.currentTimeMillis());
    }

    // Lập lịch chạy với delay 3 giây sau khi hoàn thành task trước đó
    @Scheduled(fixedDelay = 3000)
    public void runAfterThreeSeconds() {
        // System.out.println("Chạy sau 3 giây kể từ khi task trước hoàn thành: " +
        // System.currentTimeMillis());
    }

    // Lập lịch chạy theo cron expression (VD: 12:00 mỗi ngày)
    @Scheduled(cron = "0 0 12,17 * * ?")
    public void runAtNoon() {
        System.out.println("Chạy lúc 12 giờ mỗi ngày!");
    }
}
