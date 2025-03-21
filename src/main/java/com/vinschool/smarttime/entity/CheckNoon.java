package com.vinschool.smarttime.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

// @NamedNativeQuery(name = "CheckNoon.findByUserIdWorkAndTimeLineDto", query = "select C.Id as Id, C.date_work as dateWork FROM CHECK_NOON C JOIN TIME_LINE L ON C.ID_TIME_LINE = L.ID Join User u on u.id = c.id_user WHERE C.CREATEBY =:userid and L.MONTH =:timeLine", resultSetMapping = "com.vinschool.smarttime.model.response.CheckNoonResponsive")
// @SqlResultSetMapping(name = "com.vinschool.smarttime.model.response.CheckNoonResponsive", classes = @ConstructorResult(targetClass = CheckNoonResponsive.class, columns = {
//         @ColumnResult(name = "id"),
//         @ColumnResult(name = "dateWork") }))

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class CheckNoon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    String maCa;
    String nameCa;
    LocalDate dateWork;
    boolean isCheck;
    String note;
    String idUserTrans;
    String idUser;
    String createBy;
    Date createDate;
    String updateBy;
    Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_time_line")
    private TimeLine timeLine;
}
