package com.vinschool.smarttime.model.response;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class TimeSheetResponsive {
    String id;
    String thu;
    String nameTimeSheet;
    String categoryClassId;
    String categoryClassName;
    String categoryPeriodId;
    String categoryPeriodName;
    String categorySubjectId;
    String categorySubjectName;
    String categoryRoomId;
    String categoryRoomName;
    String userId;
    String userFullName;
    String noteBookId;
    String lessonTopic;
    Integer studentErrorCount;
    String detailErrorStudent;
    String generalComment;
    Double lessonEvaluation;
    Date createDateOfNoteBook;
    Date updateDateOfNoteBook;
    String createByOfNoteBook;
    String updateByOfNoteBook;
    Boolean isTrain;

    public TimeSheetResponsive(String id, String thu, String nameTimeSheet, String categoryClassId,
            String categoryClassName, String categoryPeriodId, String categoryPeriodName, String categorySubjectId,
            String categorySubjectName, String categoryRoomId, String categoryRoomName, String userId,
            String userFullName, Boolean isTrain) {
        this.id = id;
        this.thu = thu;
        this.nameTimeSheet = nameTimeSheet;
        this.categoryClassId = categoryClassId;
        this.categoryClassName = categoryClassName;
        this.categoryPeriodId = categoryPeriodId;
        this.categoryPeriodName = categoryPeriodName;
        this.categorySubjectId = categorySubjectId;
        this.categorySubjectName = categorySubjectName;
        this.categoryRoomId = categoryRoomId;
        this.categoryRoomName = categoryRoomName;
        this.userId = userId;
        this.userFullName = userFullName;
        if (isTrain == null) {
            this.isTrain = isTrain;
        } else {
            this.isTrain = isTrain;
        }
    }

    public TimeSheetResponsive(String id, String thu, String nameTimeSheet, String categoryClassId,
            String categoryClassName, String categoryPeriodId, String categoryPeriodName, String categorySubjectId,
            String categorySubjectName, String categoryRoomId, String categoryRoomName, String userId,
            String userFullName) {
        this.id = id;
        this.thu = thu;
        this.nameTimeSheet = nameTimeSheet;
        this.categoryClassId = categoryClassId;
        this.categoryClassName = categoryClassName;
        this.categoryPeriodId = categoryPeriodId;
        this.categoryPeriodName = categoryPeriodName;
        this.categorySubjectId = categorySubjectId;
        this.categorySubjectName = categorySubjectName;
        this.categoryRoomId = categoryRoomId;
        this.categoryRoomName = categoryRoomName;
        this.userId = userId;
        this.userFullName = userFullName;
    }

}
