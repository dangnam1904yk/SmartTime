package com.vinschool.smarttime.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponsive {
    private String userId;
    private String title;
    private String content;
}
