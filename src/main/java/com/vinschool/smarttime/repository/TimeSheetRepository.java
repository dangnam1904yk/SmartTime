package com.vinschool.smarttime.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.model.response.TimeSheetChekNotification;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, String> {
        List<TimeSheet> findByTimeLineId(String id);

        List<TimeSheet> findByUserIdAndTimeLineId(String userid, String timeLineId);

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f  where  g.Id =:timeLine and f.Id =:userId order by a.thu asc  ")
        List<TimeSheetResponsive> findTimeSheetByIdTimeLineAndUserIdTeach(@Param("timeLine") String timeLine,
                        @Param("userId") String userId);

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f  where  g.Id =:timeLine order by a.thu asc ")
        List<TimeSheetResponsive> findTimeSheetByIdTimeLine(@Param("timeLine") String timeLine);

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName, k.id, k.lessonTopic, k.studentErrorCount,k.detailErrorStudent, k.generalComment,k.lessonEvaluation,k.createDate, k.updateDate, k.createBy, k.updateBy, k.isTrain, g.startDate, g.endDate ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f   JOIN a.noteBook k where  g.Id =:timeLine and f.Id =:userId ")
        List<TimeSheetResponsive> findTimeSheetByIdTimeLineAndUserIdTeachWithNoteBook(
                        @Param("timeLine") String timeLine,
                        @Param("userId") String userId);

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName, k.id, k.lessonTopic, k.studentErrorCount,k.detailErrorStudent, k.generalComment,k.lessonEvaluation,k.createDate, k.updateDate, k.createBy, k.updateBy, k.isTrain, g.startDate, g.endDate ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f  JOIN a.noteBook k where  g.Id =:timeLine ")
        List<TimeSheetResponsive> findTimeSheetByIdTimeLineWithNoteBook(@Param("timeLine") String timeLine);

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName, k.id, k.lessonTopic, k.studentErrorCount,k.detailErrorStudent, k.generalComment,k.lessonEvaluation,k.createDate, k.updateDate, k.createBy, k.updateBy, k.isTrain, g.startDate, g.endDate ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f  JOIN a.noteBook k where   f.Id =:userId ")
        List<TimeSheetResponsive> findTimeSheetByUserIdTeachWithNoteBook(@Param("userId") String userId);

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName, k.id, k.lessonTopic, k.studentErrorCount,k.detailErrorStudent, k.generalComment,k.lessonEvaluation,k.createDate, k.updateDate, k.createBy, k.updateBy, k.isTrain, g.startDate, g.endDate ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f    JOIN a.noteBook k ")
        List<TimeSheetResponsive> findTimeSheetWithNoteBook();

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName, k.id, k.lessonTopic, k.studentErrorCount,k.detailErrorStudent, k.generalComment,k.lessonEvaluation,k.createDate, k.updateDate, k.createBy, k.updateBy, k.isTrain, g.startDate, g.endDate ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f    JOIN a.noteBook k  where k.id=:id")
        TimeSheetResponsive findNoteBookByIdWWithTimeSheet(@Param("id") String id);

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f  order by a.thu asc  ")
        List<TimeSheetResponsive> getAllTimeSheetResponsive();

        @Query("SELECT new com.vinschool.smarttime.model.response.TimeSheetResponsive(a.id, a.thu, g.nameTimeLine, b.id, b.className, c.id, c.namePeriod, d.id, d.nameSubject, e.id, e.nameRoom, f.id, f.fullName ) "
                        +
                        "FROM TimeSheet a JOIN a.timeLine g JOIN  a.categoryClass b  JOIN a.categoryPeriod c JOIN a.categorySubject d "
                        +
                        " JOIN a.categoryRoom  e JOIN a.user f where f.id =:id order by a.thu asc  ")
        List<TimeSheetResponsive> getTimeSheetResponsiveByUserIdTeach(@Param("id") String userId);

        @Query("Select new com.vinschool.smarttime.model.response.TimeSheetChekNotification(b.id, b.startDate, b.endDate, c.id, c.hourStart, c.hourEnd, a.id,a.thu,e.id, e.isTrain, e.createDate, d.id )"
                        + "From TimeSheet a  join a.timeLine  b join a.categoryPeriod c join a.user d left join a.noteBook e"
                        + " where  b.endDate >=:dateCheck and b.isActive =:isActive  and a.thu =:thu and d.id =:userId order by a.thu desc")
        List<TimeSheetChekNotification> CheckSoDauBai(@Param("dateCheck") LocalDate dateCheck,
                        @Param("isActive") Boolean isActive,
                        @Param("isTrain") Boolean isTrain,
                        @Param("thu") String thu,
                        @Param("userId") String userId);
}
