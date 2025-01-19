package com.vinschool.smarttime.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.TimeLine;
import com.vinschool.smarttime.model.dto.TimeLineDto;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;

@Repository
public interface TimeLineRepository extends JpaRepository<TimeLine, String> {
    List<TimeLine> findByType(int type);

    @Query("SELECT new com.vinschool.smarttime.model.dto.TimeLineDto(a.id,a.month, a.isActive, a.type, a.nameTimeLine, a.startDate, a.endDate, a.calendarType ) "
            +
            "FROM TimeLine a Where a.type =:type")
    List<TimeLineDto> findTimeLineByType(@Param("type") int type);

    List<TimeLine> findByEndDateAfterAndType(LocalDate date, int type);
}
