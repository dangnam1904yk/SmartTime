package com.vinschool.smarttime.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.vinschool.smarttime.entity.DetailNotification;
import com.vinschool.smarttime.entity.Notification;
import com.vinschool.smarttime.model.response.NotificationResponsive;
import com.vinschool.smarttime.model.response.TimeSheetChekNotification;
import com.vinschool.smarttime.repository.DetailNotificationRepository;
import com.vinschool.smarttime.repository.NotificationRepository;
import com.vinschool.smarttime.ulti.Constant;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private TimeSheetService timeSheetService;

    @Autowired
    private UserService userService;
    @Autowired
    private TimeLineService timeLineService;

    @Autowired
    private DetailNotificationRepository detailNotificationRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendScheduledNotifications(String accountId, String message) {
        // Xử lý logic gửi thông báo
        String notificationMessage = "Chạy cho tài khoản: " + accountId + " với thông báo: " + message;
        LocalDate curenDate = LocalDate.now();
        DayOfWeek dayOfWeek = curenDate.getDayOfWeek();
        String thu = "";
        if (dayOfWeek.getValue() == 1) {
            thu = "T2";
        }
        if (dayOfWeek.getValue() == 2) {
            thu = "T3";
        }
        if (dayOfWeek.getValue() == 3) {
            thu = "T4";
        }
        if (dayOfWeek.getValue() == 4) {
            thu = "T5";
        }
        if (dayOfWeek.getValue() == 5) {
            thu = "T6";
        }
        if (dayOfWeek.getValue() == 6) {
            thu = "T7";
        }
        if (dayOfWeek.getValue() == 7) {
            thu = "CN";
        }
        List<TimeSheetChekNotification> list = timeSheetService.CheckSoDauBai(LocalDate.now(), Constant.SATATUS.ACTIVE,
                Constant.SATATUS_TRAIN.YES, thu, accountId);

        NotificationResponsive notificationResponsive = new NotificationResponsive();
        notificationResponsive.setUserId(accountId);
        for (TimeSheetChekNotification data : list) {

            LocalTime startTime = LocalTime.of(18, 0); // 09:00
            LocalTime endTime = LocalTime.of(19, 0); // 17:00
            LocalTime checkTime = LocalTime.now(); // 14:30

            // Kiểm tra nếu giờ nằm trong khoảng
            boolean isInRange = isTimeInRange(checkTime, startTime, endTime);
            if (isInRange == true) {
                if (data.getNoteBookId() == null) {
                    Notification notification = new Notification();
                    notification.setTitle("Thông báo xử phạt, bạn đã quá giờ cho phép nhập sổ");
                    notification.setContent(
                            "Bạn sẽ bị xử phạt với số tiền 50.000đ cho một lỗi quên nhập tiết");

                    notification.setCreateDate(new Date());
                    notification.setUpdateDate(new Date());
                    notificationResponsive.setTitle(notification.getTitle());
                    notificationResponsive.setContent(notification.getContent());
                    Notification notiSave = notificationRepository.save(notification);
                    DetailNotification detailNotification = new DetailNotification();
                    detailNotification.setNotification(notification);
                    detailNotification.setUser(userService.findById(data.getUserId()));
                    detailNotificationRepository.save(detailNotification);
                }

                if (data.getNoteBookId() != null && data.getNoteBookIsTrain() == true) {
                    Notification notification = new Notification();
                    notification.setTitle("Chúc mừng bạn đã hoàn thành nhập sổ đầu bài ngày hôm nay");
                    notification.setContent(
                            "Chúc mừng bạn đã hoàn thành nhập sổ đầu bài ngày hôm nay");
                    notification.setCreateDate(new Date());
                    notification.setUpdateDate(new Date());
                    notificationResponsive.setTitle(notification.getTitle());
                    notificationResponsive.setContent(notification.getContent());
                    Notification notiSave = notificationRepository.save(notification);
                    DetailNotification detailNotification = new DetailNotification();
                    detailNotification.setNotification(notification);
                    detailNotification.setUser(userService.findById(data.getUserId()));
                    detailNotificationRepository.save(detailNotification);
                }
            } else {
                if (data.getNoteBookId() == null) {
                    Notification notification = new Notification();
                    notification.setTitle("Thông báo, bạn chưa nhập sổ đầu bài ngày hôm nay");
                    notification.setContent(
                            "Bạn chưa nhập sổ đầu bài ngày hôm này, vui lòng đăng nhập vào hệ thống để nhập ngay nhé ");
                    notification.setCreateDate(new Date());
                    notification.setUpdateDate(new Date());
                    notificationResponsive.setTitle(notification.getTitle());
                    notificationResponsive.setContent(notification.getContent());
                    Notification notiSave = notificationRepository.save(notification);
                    DetailNotification detailNotification = new DetailNotification();
                    detailNotification.setNotification(notification);
                    detailNotification.setUser(userService.findById(data.getUserId()));
                    detailNotificationRepository.save(detailNotification);
                }

                if (data.getNoteBookId() != null && data.getNoteBookIsTrain() == false) {
                    Notification notification = new Notification();
                    notification.setTitle("Thông báo, bạn chưa đánh dấu đã dạy ");
                    notification.setContent(
                            "Bạn chưa nhập sổ đầu bài ngày hôm này, vui lòng đăng nhập vào hệ thống để xử lý ngay nhé ");
                    notification.setCreateDate(new Date());
                    notification.setUpdateDate(new Date());
                    notificationResponsive.setTitle(notification.getTitle());
                    notificationResponsive.setContent(notification.getContent());
                    Notification notiSave = notificationRepository.save(notification);
                    DetailNotification detailNotification = new DetailNotification();
                    detailNotification.setNotification(notification);
                    detailNotification.setUser(userService.findById(data.getUserId()));
                    detailNotificationRepository.save(detailNotification);
                }
            }

        }
        if (notificationResponsive.getTitle() != null && !notificationResponsive.getTitle().isEmpty()) {

            messagingTemplate.convertAndSend("/topic/notifications", notificationResponsive);
        }
    }

    private static boolean isTimeInRange(LocalTime time, LocalTime start, LocalTime end) {
        // Nếu endTime trước startTime (qua nửa đêm), xử lý đặc biệt
        if (end.isBefore(start)) {
            return !time.isBefore(start) || !time.isAfter(end);
        }
        // Thời gian thông thường
        return !time.isBefore(start) && !time.isAfter(end);
    }

}
