package com.vinschool.smarttime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.vinschool.smarttime.entity.Notification;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.repository.DetailNotificationRepository;
import com.vinschool.smarttime.service.NotificationService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationCotrontroller {

    @Autowired
    private NotificationService notificationService;

}
