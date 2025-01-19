package com.vinschool.smarttime.model.response;

import java.time.LocalDate;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class TimeSheetChekNotification {

    String timeLineid;
    LocalDate timeLineStartDate;
    LocalDate timeLineEndDate;
    String categoryPeriodId;
    String categoryPeriodHourStart;
    String categoryPeriodHourEnd;
    String timeSheetId;
    String timeSheetThu;
    String noteBookId;
    Boolean noteBookIsTrain;
    Date noteBookCreateDate;
    String userId;

}
