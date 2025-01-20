package com.vinschool.smarttime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.vinschool.smarttime.entity.AccountSchedule;
import com.vinschool.smarttime.repository.AccountScheduleRepository;
import com.vinschool.smarttime.service.SchedulerService;

@Component
public class SchedulerInitializer {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private AccountScheduleRepository scheduleConfigRepository;

    @Bean
    public ApplicationRunner initializeSchedules() {
        return args -> {
            // Lấy danh sách lịch trình từ database
            scheduleConfigRepository.findAll().forEach(schedule -> {
                schedulerService.scheduleNotification(
                        schedule.getUser().getId(),
                        schedule.getUser().getFullName(),
                        schedule.getCronExpression());
            });
        };
    }
}
