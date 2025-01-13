package com.vinschool.smarttime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartTimeApplication.class, args);
	}

}
