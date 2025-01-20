package com.vinschool.smarttime.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_schedules")
@Data
public class AccountSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "cron_expression", nullable = false)
    private String cronExpression;

    @Column(name = "status", nullable = false)
    private String status = "ACTIVE";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @OneToOne
    private User user;

    public String getCronExpression() {
        // if (!this.cronExpression.contains(";")) {
        // this.cronExpression = this.cronExpression.trim() + ";"; // Thêm dấu `,` nếu
        // thiếu
        // }
        // String[] timeExpressions = this.cronExpression.split(",");
        // StringBuilder hours = new StringBuilder();
        // String seconds = "0"; // Mặc định là 0 nếu không có
        // String minutes = "0"; // Mặc định là 0 nếu không có

        // for (String expression : timeExpressions) {
        // String currentSeconds = "0";
        // String currentMinutes = "0";
        // String currentHours = "*"; // Mặc định là '*' nếu không có thông tin giờ

        // expression = expression.trim();

        // // Kiểm tra và trích xuất thông tin giây
        // if (expression.contains("s")) {
        // String[] parts = expression.split("s");
        // currentSeconds = parts[0].replaceAll(".*[hm]", "").trim();
        // }

        // // Kiểm tra và trích xuất thông tin phút
        // if (expression.contains("m")) {
        // String[] parts = expression.split("m");
        // currentMinutes = parts[0].replaceAll(".*h", "").trim();
        // if (parts.length > 1 && parts[1].contains("s")) {
        // currentSeconds = parts[1].split("s")[0].trim();
        // }
        // }

        // // Kiểm tra và trích xuất thông tin giờ
        // if (expression.contains("h")) {
        // String[] parts = expression.split("h");
        // currentHours = parts[0].trim();
        // if (parts.length > 1) {
        // if (parts[1].contains("h")) {
        // // Trường hợp 7h22h12s
        // String[] hourParts = parts[1].split("h");
        // hours.append(hourParts[0]).append(","); // Thêm giờ đầu tiên
        // if (hourParts.length > 1 && hourParts[1].contains("s")) {
        // currentSeconds = hourParts[1].split("s")[0].trim();
        // }
        // } else if (parts[1].contains("m")) {
        // // Trường hợp 7h22m
        // currentMinutes = parts[1].split("m")[0].trim();
        // }
        // }
        // }

        // // Gom nhóm giờ
        // hours.append(currentHours).append(",");
        // seconds = currentSeconds;
        // minutes = currentMinutes;
        // }

        // // Xóa dấu phẩy cuối cùng
        // if (hours.length() > 0) {
        // hours.setLength(hours.length() - 1);
        // }

        // // Kết hợp thành biểu thức cron
        // return String.format("%s %s %s * * ?", seconds, minutes, hours.toString());
        return this.cronExpression;
    }

}
