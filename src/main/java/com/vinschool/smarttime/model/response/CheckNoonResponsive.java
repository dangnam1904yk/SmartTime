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
public class CheckNoonResponsive {
    String id;
    LocalDate dateWork;
    String idUserTrans;
    boolean isCheck;
    String maCa;
    String nameCa;
    String note;
    String fullName;
    String idUser;
    String idTimeLine;
    String createBy;
    Date createDate;
    boolean isActive;
    String updateBy;
    Date updateDate;
    String month;
    String email;
}
