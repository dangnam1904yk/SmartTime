package com.vinschool.smarttime.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        return new SchedulerFactoryBean();
    }

    // Tạo lịch trình
    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(NotificationJob.class)
                .withIdentity("sampleJob")
                .storeDurably()
                .build();
    }
}
