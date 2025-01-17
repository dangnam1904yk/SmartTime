package com.vinschool.smarttime.model.request;

import java.time.LocalDate;
import java.util.Date;

import com.vinschool.smarttime.entity.TimeLine;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class CheckNoonRequest {
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
    TimeLine timeLine;
}
